package com.zhangjun.excel.controller;

import com.alibaba.excel.write.metadata.WriteWorkbook;
import com.zhangjun.excel.common.api.CommonResult;
import com.zhangjun.excel.mbg.model.SupplementTable;
import com.zhangjun.excel.service.impl.EasyExcelExportService;
import com.zhangjun.excel.service.impl.EasyExcelInputService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @Author zhangjun
 * @Date 2025/6/7 16:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/easyexcel")
@Slf4j
public class EasyExcelController {

    @Autowired
    private EasyExcelInputService easyExcelInputService;

    @Autowired
    private EasyExcelExportService easyExcelExportService;

    @Operation(summary = "上传基础配置文档")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<?> uploadExcel(@RequestPart("file") MultipartFile file) {
        try {
            easyExcelInputService.readMultiSheetExcel(file);
            return CommonResult.success("知识库更新成功！");

        } catch (Exception e) {
            log.error("Excel读取失败", e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @Operation(summary = "获取知识库知识并更新表格")
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<?> exportMultipleSheets(@RequestPart("file") MultipartFile file, HttpServletResponse response) throws IOException, InstantiationException, IllegalAccessException {

        Map<String, List<?>> dataMap = null;
        List<SupplementTable> supplementTableList = null;
        //新模版表格数据获取
        dataMap = easyExcelInputService.updateMultiSheetExcel(file);

        //缺失待补充表格数据获取
        supplementTableList = easyExcelInputService.supplementTable(dataMap);

        //获取上传文件的原始名称（包含扩展名）
        String originalName = file.getOriginalFilename();
        //通过字符串处理分离文件名和扩展名
        String fileName = originalName.substring(0, originalName.lastIndexOf("."));
        //新模版表格
        String newFileName = fileName + "新模版-" + new Date().getTime() + ".xlsx";
        easyExcelExportService.exportMultipleSheets(newFileName, dataMap);

        //缺失待补充表格
        String supplementTableFileName = "缺失翻译待补充表-" + new Date().getTime() + ".xlsx";
        easyExcelExportService.exportSupplementTable(supplementTableFileName, supplementTableList);


        String zipname = "压缩文件" + new Date().getTime() + ".zip";
        response.setContentType("application/zip");
        // RFC 5987编码规范
        String encodedName = URLEncoder.encode(zipname, "UTF-8")
                .replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("GBK"), "ISO-8859-1") + "\";" +
                " filename*=UTF-8''" + encodedName);

        try {
            zipFiles(response, new String[]{newFileName, supplementTableFileName});

            return CommonResult.success("表格更新成功，文件已开始下载");
        } catch (IOException e) {
            log.error("Excel更新失败", e);
            return CommonResult.failed(e.getMessage());
        }

    }

    /**
     * 将多个文件压缩到一个Zip文件中
     */
    private static void zipFiles(HttpServletResponse response, String[] filePaths) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[1024];

            for (String filePath : filePaths) {
                File file = new File(filePath);
                String fileName = file.getName();

                try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
                    // 添加Zip条目
                    zos.putNextEntry(new ZipEntry(fileName));

                    // 写入文件内容
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    // 关闭当前条目
                    zos.closeEntry();
                }
            }
        }
    }

}
