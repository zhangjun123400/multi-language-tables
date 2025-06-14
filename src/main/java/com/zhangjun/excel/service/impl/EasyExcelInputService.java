package com.zhangjun.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.listener.*;
import com.zhangjun.excel.mbg.mapper.*;
import com.zhangjun.excel.mbg.model.*;
import com.zhangjun.excel.processor.SheetProcessor;
import com.zhangjun.excel.processor.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author zhangjun
 * @Date 2025/6/7 16:04
 * @Version 1.0
 */
@Service
@Slf4j
public class EasyExcelInputService {

    @Autowired
    private BaseConfigurationsMapper baseConfigurationsMapper;
    @Autowired
    private FunctionDefinitionMapper functionDefinitionMapper;
    @Autowired
    private NetworkConfigurationGuideMapper networkConfigurationGuideMapper;
    @Autowired
    private MessagePushMapper messagePushMapper;
    @Autowired
    private AutomationMapper automationMapper;

    /**
     * 多语言知识库新增和更新
     * @param file
     * @throws Exception
     */
    public void readMultiSheetExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        // 创建 ExcelReader
        ExcelReader excelReader =  EasyExcel.read(inputStream).build();
        //获取所有Sheet
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        for (ReadSheet sheet : sheets) {
            String sheetName = sheet.getSheetName();
            if("基础配置".equals(sheetName)){
                EasyExcel.read(file.getInputStream(), BaseConfigurations.class, new BaseConfigurationsReadListener(baseConfigurationsMapper)).sheet(sheetName).doRead();
            } else if ("功能定义".equals(sheetName)) {
                EasyExcel.read(file.getInputStream(), FunctionDefinition.class, new FunctionReadListener(functionDefinitionMapper)).sheet(sheetName).doRead();
            }else if ("配网引导".equals(sheetName)) {
                EasyExcel.read(file.getInputStream(), NetworkConfigurationGuide.class, new NetworkConfigurationGuideReadListener(networkConfigurationGuideMapper)).sheet(sheetName).doRead();
            }else if ("消息推送".equals(sheetName)) {
                EasyExcel.read(file.getInputStream(), MessagePush.class, new MessagePushReadListener(messagePushMapper)).sheet(sheetName).doRead();
            }else if ("自动化".equals(sheetName)) {
                EasyExcel.read(file.getInputStream(), Automation.class, new AutomationReadListener(automationMapper)).sheet(sheetName).doRead();
            }

        }
    }


    /**
     * 导入的表格数据和数据库进行比较和更新
     * @param file
     * @return
     * @throws IOException
     */
    public Map<String, List<?>> updateMultiSheetExcel(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            ExcelReader excelReader = EasyExcel.read(inputStream).build();
            List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();

            Map<String, SheetProcessor> processors = createProcessors();
            Map<String, List<?>> resultMap = new LinkedHashMap<>();

            for (ReadSheet sheet : sheets) {
                String sheetName = sheet.getSheetName();
                SheetProcessor processor = processors.get(sheetName);

                if (processor != null) {
                    if ("基础配置".equals(sheetName)) {
                        resultMap.put(sheetName, processor.processSheet(file, sheetName,baseConfigurationsMapper));
                    } else if ("功能定义".equals(sheetName)) {
                        resultMap.put(sheetName, processor.processSheet(file, sheetName,functionDefinitionMapper));
                    }else if ("配网引导".equals(sheetName)) {
                        resultMap.put(sheetName, processor.processSheet(file, sheetName,networkConfigurationGuideMapper));
                    }else if ("消息推送".equals(sheetName)) {
                        resultMap.put(sheetName, processor.processSheet(file, sheetName,messagePushMapper));
                    } else if ("自动化".equals(sheetName)) {
                        resultMap.put(sheetName, processor.processSheet(file, sheetName,automationMapper));
                    }

                } else {
                    log.warn("未处理的Sheet: {}", sheetName);
                }
            }
            return resultMap;
        }
    }


    /**
     *
     * @param dataMap
     * @return
     * @throws IOException
     */
    public List<SupplementTable> supplementTable(Map<String, List<?>> dataMap) throws IOException, InstantiationException, IllegalAccessException {
        Map<String, List<?>> result = new HashMap<>(dataMap);

        List<SupplementTable> supplementTableList = new ArrayList<>();

        for(Map.Entry<String, List<?>> entry : result.entrySet()) {

            switch (entry.getKey()) {
                case "基础配置":{
                    List<BaseConfigurations> baseConfigurationsList = (List<BaseConfigurations>)entry.getValue();
                    for (BaseConfigurations item : baseConfigurationsList) {
                        if (!ExcelExportUtil.isAllFieldsNotNull(item)) {
                            SupplementTable supplementTable = new SupplementTable();
                            supplementTable.setSheetName("基础配置");
                            ExcelExportUtil.copyNonNullAsNA(item,supplementTable);
                            supplementTableList.add(supplementTable);
                        }

                    }
                }break;
                case "配网引导":{
                    List<NetworkConfigurationGuide> networkConfigurationGuideList = (List<NetworkConfigurationGuide>)entry.getValue();
                    for (NetworkConfigurationGuide item : networkConfigurationGuideList) {
                        if (!ExcelExportUtil.isAllFieldsNotNull(item)) {
                            SupplementTable supplementTable = new SupplementTable();
                            supplementTable.setSheetName("配网引导");
                            ExcelExportUtil.copyNonNullAsNA(item,supplementTable);
                            supplementTableList.add(supplementTable);
                        }
                    }
                } break;
                case "功能定义":{
//                    List<FunctionDefinition> functionDefinitionList = (List<FunctionDefinition>)entry.getValue();
//                    for (FunctionDefinition item : functionDefinitionList) {
//                        ExcelExportUtil.copyNonNullAsNA(item,supplementTable);
//                    }

                }break;
                case "消息推送":{
                    List<MessagePush> messagePushList = (List<MessagePush>)entry.getValue();
                    for (MessagePush item : messagePushList) {
                        if (!ExcelExportUtil.isAllFieldsNotNull(item)) {
                            SupplementTable supplementTable = new SupplementTable();
                            supplementTable.setSheetName("消息推送");
                            ExcelExportUtil.copyNonNullAsNA(item,supplementTable);
                            supplementTableList.add(supplementTable);
                        }
                    }

                } break;
                case "自动化":{
                    List<Automation> automationList = (List<Automation>)entry.getValue();
                    for (Automation item : automationList) {
                        if (!ExcelExportUtil.isAllFieldsNotNull(item)) {
                            SupplementTable supplementTable = new SupplementTable();
                            supplementTable.setSheetName("自动化");
                            ExcelExportUtil.copyNonNullAsNA(item,supplementTable);
                            supplementTableList.add(supplementTable);
                        }
                    }

                } break;
            }


        }
        return supplementTableList;

    }

    // 创建处理器映射
    private Map<String, SheetProcessor> createProcessors() {
        Map<String, SheetProcessor> processors = new HashMap<>();

        processors.put("基础配置", new BaseConfigProcessor());
        processors.put("配网引导", new NetworkGuideProcessor());
        processors.put("功能定义", new FunctionDefinitionProcessor());
        processors.put("消息推送", new MessagePushProcessor());
        processors.put("自动化", new AutomationProcessor());

        return processors;
    }


}
