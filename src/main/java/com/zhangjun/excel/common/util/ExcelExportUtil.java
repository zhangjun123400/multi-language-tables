package com.zhangjun.excel.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteWorkbook;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhangjun.excel.mbg.model.SupplementTable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

/**
 * @Author zhangjun
 * @Date 2025/6/7 20:24
 * @Version 1.0
 */
public class ExcelExportUtil {

    /**
     * 导出复杂表格
     *
     * @param file
     * @param sheetDataMap
     * @throws IOException
     */
    public static void exportMultiSheet(String file, Map<String, List<?>> sheetDataMap) throws IOException {

        ExcelWriter excelWriter = EasyExcel.write(file).build();
        try {
            sheetDataMap.forEach((sheetName, dataList) -> {
                if (!dataList.isEmpty()) {
                    WriteSheet writeSheet = EasyExcel.writerSheet(sheetName)
                            .head(dataList.get(0).getClass()).build();
                    excelWriter.write(dataList, writeSheet);
                }
            });
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

    }

    // 通用读取方法
    public static <T> List<T> readSheet(MultipartFile file, String sheetName, Class<T> clazz) {
        try {
            return EasyExcel.read(file.getInputStream())
                    .head(clazz)
                    .sheet(sheetName)
                    .doReadSync();
        } catch (IOException e) {
            throw new RuntimeException("读取Sheet失败: " + sheetName, e);
        }
    }

    /**
     * 导出简单表格
     *
     * @param file
     * @param supplementTables
     * @throws IOException
     */
    public static void exportSupplementTable(String file, List<SupplementTable> supplementTables)
            throws IOException {

        ExcelWriter writer = null;
        try {
            writer = EasyExcel.write(file, SupplementTable.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .build();
            writer.write(supplementTables, EasyExcel.writerSheet("Sheet1").build());
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }
    }

    /**
     * 属性复制，但是空属性不复制
     *
     * @param src
     * @param target
     */
    public static void safeCopyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target,
                Arrays.stream(src.getClass().getDeclaredFields())
                        .filter(field -> {
                            try {
                                field.setAccessible(true);
                                return field.get(src) == null; // 返回需要忽略的字段名
                            } catch (IllegalAccessException e) {
                                return false;
                            }
                        })
                        .map(Field::getName)
                        .toArray(String[]::new));
    }

    /**
     * 属性复制，但是非空属性全部改为“NA”
     *
     * @param src
     * @param target
     */
    public static void copyNonNullAsNA(Object src, Object target) throws InstantiationException, IllegalAccessException {
        // 创建临时拷贝对象
        Object temp = src.getClass().newInstance();
        BeanUtils.copyProperties(src, temp);

        // 处理临时对象的字段
        Field[] fields = temp.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(temp);
                if (value != null) {
                    if (field.getType() == Integer.class) return;
                    if ("simplifiedChinese".equals(field.getName())) return;
                    if ("english".equals(field.getName())) return;

                    field.set(temp, field.getType() == String.class
                            ? "NA"
                            : String.valueOf(value));
                }
            } catch (Exception ignored) {
            }
        });

        // 将处理后的临时对象拷贝到target
        BeanUtils.copyProperties(temp, target);

    }

}
