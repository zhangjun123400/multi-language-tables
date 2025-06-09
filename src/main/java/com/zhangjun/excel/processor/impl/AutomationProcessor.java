package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.model.Automation;
import com.zhangjun.excel.processor.SheetProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 23:04
 * @Version 1.0
 */
public class AutomationProcessor implements SheetProcessor<Automation> {
    @Override
    public List<Automation> processSheet(MultipartFile file, String sheetName, BaseMapper<Automation> baseMapper) {
        List<Automation> list = ExcelExportUtil.readSheet(file, sheetName, Automation.class);

        for (Automation item : list) {

            LambdaQueryWrapper<Automation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Automation::getNameClassification, item.getNameClassification())
                    .eq(Automation::getSimplifiedChinese, item.getSimplifiedChinese());

            Automation existing = baseMapper.selectOne(queryWrapper);
            if (existing != null) {
                existing.setConfigurationId(item.getConfigurationId());
                existing.setConfigurationName(item.getConfigurationName());
                existing.setNameClassification(item.getNameClassification());
                ExcelExportUtil.safeCopyProperties(existing, item);
            }
        }
        return list;
    }
}
