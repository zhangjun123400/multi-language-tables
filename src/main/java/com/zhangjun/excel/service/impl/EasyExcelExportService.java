package com.zhangjun.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.zhangjun.excel.common.util.ExcelExportUtil;
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

    public void exportMultipleSheets(HttpServletResponse response,String fileName, Map<String, List<?>> dataMap) {

        try {
            ExcelExportUtil.exportMultiSheet(response, fileName, dataMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
