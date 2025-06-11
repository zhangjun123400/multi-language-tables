package com.zhangjun.excel.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhangjun.excel.mbg.model.SupplementTable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/6/7 20:24
 * @Version 1.0
 */
public class ExcelExportUtil {
    public static void exportMultiSheet(HttpServletResponse response,
                                        String fileName, Map<String, List<?>> sheetDataMap) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");

        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build()) {
            sheetDataMap.forEach((sheetName, dataList) -> {
                WriteSheet writeSheet = EasyExcel.writerSheet(sheetName)
                        .head(dataList.get(0).getClass()).build();
                excelWriter.write(dataList, writeSheet);
            });
        }
    }

    // 通用读取方法
    public static  <T> List<T> readSheet(MultipartFile file, String sheetName, Class<T> clazz) {
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
     *导出简单表格
     * @param response
     * @param fileName
     * @param supplementTables
     * @throws IOException
     */
    public static void exportSupplementTable(HttpServletResponse response,
                                      String fileName,List<SupplementTable> supplementTables) throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");

        EasyExcel.write(response.getOutputStream(), SupplementTable.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())  // 自动调整列宽
                .sheet("1")
                .doWrite(supplementTables);
    }

    /**
     * 属性复制，但是空属性不复制
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
                        .toArray(String[]::new)
        );
    }

    /**
     *属性复制，但是非空属性全部改为“NA”
     * @param src
     * @param target
     */
    public static void safeCopyProperties1(Object src, Object target) {
        BeanUtils.copyProperties(src, target,
                Arrays.stream(src.getClass().getDeclaredFields())
                        .filter(field -> {
                            try {
                                field.setAccessible(true);
                                if (field.get(src) != null) {
                                    field.set(src,"NA");
                                }
                                return true;
                            } catch (IllegalAccessException e) {
                                return false;
                            }
                        })
                        .map(Field::getName)
                        .toArray(String[]::new)
        );
    }
}
