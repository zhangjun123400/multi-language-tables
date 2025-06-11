package com.zhangjun.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.model.SupplementTable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/6/7 19:34
 * @Version 1.0
 */
@Service
@Slf4j
public class EasyExcelExportService {

    /**
     * 导出新学习到的模版
     * @param response
     * @param fileName
     * @param dataMap
     */
    public void exportMultipleSheets(HttpServletResponse response,String fileName, Map<String, List<?>> dataMap) {

        try {
            ExcelExportUtil.exportMultiSheet(response, fileName, dataMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 导出缺失表
     * @param response
     * @param fileName
     * @param supplementTables
     */
    public void exportSupplementTable(HttpServletResponse response,String fileName, List<SupplementTable> supplementTables) {

        try {
            ExcelExportUtil.exportSupplementTable(response, fileName, supplementTables);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
