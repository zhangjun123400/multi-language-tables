package com.zhangjun.excel;

import com.zhangjun.excel.mbg.mapper.BaseConfigurationsMapper;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ExcelApplicationTests {

    @Autowired
    private BaseConfigurationsMapper baseConfigurationsMapper;

    @Test
    void contextLoads() {
        BaseConfigurations baseConfigurations = new BaseConfigurations();
        baseConfigurations.setId(1);
        baseConfigurations.setConfigurationName("zhangjun");
        baseConfigurations.setProductModel("zhangsan");
        int count = baseConfigurationsMapper.insert(baseConfigurations);
        log.info("新增到一条数据: {}", count);
    }

}
