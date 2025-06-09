package com.zhangjun.excel;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/12 21:47
 * @Version 1.0
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/multi-language-tables?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root@123";

        /**
         * 获取数据中所有的表
         */

        // ✅ 获取所有非系统表名
        List<String> allTables = getAllTableNames(jdbcUrl, username, password, true);

        // 若表名为空则终止
        if (allTables.isEmpty()) {
            System.out.println("数据库中没有可生成的非系统表！");
            return;
        }

        /**
         * 生成model文件和mapper文件
         */
        // 1. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder(
                jdbcUrl,
                username,
                password)
                //.driver(com.mysql.cj.jdbc.Driver.class)
                .build();

        // 2. 全局配置
        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir(System.getProperty("user.dir") + "/src/main/java") // 输出路径
                .author("zhangjun") // 开发者署名
                //.fileOverride() // 覆盖旧文件
                //.enableSwagger() // 开启Swagger注解
                .enableSpringdoc()//开启Swagger3注解
                .build();


        // 3. 包路径配置
        PackageConfig pc = new PackageConfig.Builder()
                .parent("com.zhangjun.excel.mbg") // 父包名
                .entity("model")       // 实体类包名
                .mapper("mapper")      // Mapper接口包名
                //.service("service")    // Service接口包名
                //.controller("controller") // Controller包名
                .build();



        // 4. 策略配置
        StrategyConfig strategy = new StrategyConfig.Builder()

                .addInclude(allTables)
                //.addInclude("pms_brand") // 指定生成的表名
                .entityBuilder()
                .formatFileName("%s")// 禁止自动格式化文件名
                .enableFileOverride()  // 覆盖旧文件
                .naming(NamingStrategy.underline_to_camel) // 表名下划线转驼峰
                .enableLombok()     // 启用Lombok
                .enableTableFieldAnnotation() // 字段添加表注解
                .mapperBuilder()
                .enableFileOverride()
                .controllerBuilder()
                .enableRestStyle()  // 生成@RestController
                .build();


        // 模板配置（新增）
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .entity("/templates/entity.java") // 指向自定义模板
                .disable(TemplateType.XML)        // ✅ 禁用XML生成（如需XML则删除此行）
                .service(null)       // 禁用 Service
                .serviceImpl(null)     // 禁用 ServiceImpl
                .controller(null)       // 禁用 Controller
                .build();

        // 5. 执行生成
        AutoGenerator generator = new AutoGenerator(dsc);
        generator.global(gc)
                .packageInfo(pc)
                .strategy(strategy)
                .template(templateConfig) // 使用模板配置
                .execute(new FreemarkerTemplateEngine());
    }

    /**
     * 获取指定数据库的所有非系统表名（过滤MySQL系统表）
     * @param jdbcUrl  数据库连接URL（需指定具体数据库名，如 jdbc:mysql://localhost:3306/your_db）
     * @param username 用户名
     * @param password 密码
     * @param filterSystemTable 是否过滤系统表（如mysql/sys/information_schema等库的表）
     * @return 表名列表
     */
    public static List<String> getAllTableNames(String jdbcUrl, String username, String password, boolean filterSystemTable) {
        List<String> tables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metaData = conn.getMetaData();
            // 获取指定类型的表（TABLE表示用户表，SYSTEM TABLE为系统表）
            ResultSet rs = metaData.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"});

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String schema = rs.getString("TABLE_SCHEM");
                // 根据需要过滤系统表
                if (filterSystemTable) {
                    if (schema != null && schema.toLowerCase().matches("(mysql|information_schema|sys|performance_schema)")) {
                        continue;
                    }
                }

                tables.add(tableName);

            }
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库表名失败: " + e.getMessage(), e);
        }
        return tables;
    }


}
