package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import com.zhangjun.excel.mbg.model.FunctionDefinition;
import com.zhangjun.excel.processor.SheetProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 22:56
 * @Version 1.0
 */
public class FunctionDefinitionProcessor implements SheetProcessor<FunctionDefinition> {
    @Override
    public List<FunctionDefinition> processSheet(MultipartFile file, String sheetName, BaseMapper<FunctionDefinition> baseMapper) {
        List<FunctionDefinition> list = ExcelExportUtil.readSheet(file, sheetName, FunctionDefinition.class);

        for (FunctionDefinition item : list) {

            LambdaQueryWrapper<FunctionDefinition> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FunctionDefinition::getConfigurationName,  item.getConfigurationName())
                    .eq(FunctionDefinition::getSimplifiedChinese,item.getSimplifiedChinese());

            FunctionDefinition existing = baseMapper.selectOne(queryWrapper);
            if (existing != null) {
                existing.setConfigurationId(item.getConfigurationId());
                existing.setConfigurationName(item.getConfigurationName());
                ExcelExportUtil.safeCopyProperties(existing, item);
            }
        }
        return list;
    }
}