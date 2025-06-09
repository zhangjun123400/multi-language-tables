package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import com.zhangjun.excel.processor.SheetProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 22:38
 * @Version 1.0
 */
public class BaseConfigProcessor implements SheetProcessor<BaseConfigurations> {
    @Override
    public List<BaseConfigurations> processSheet(MultipartFile file, String sheetName,  BaseMapper<BaseConfigurations> baseMapper) {
        List<BaseConfigurations> list = ExcelExportUtil.readSheet(file, sheetName, BaseConfigurations.class);

        for (BaseConfigurations item : list) {
            LambdaQueryWrapper<BaseConfigurations> query = new LambdaQueryWrapper<>();
            query.eq(BaseConfigurations::getProductModel, item.getProductModel())
                    .eq(BaseConfigurations::getConfigurationName, item.getConfigurationName());

            BaseConfigurations existing = baseMapper.selectOne(query);

            if (existing != null) {
                existing.setProductModel(item.getProductModel());
                existing.setConfigurationName(item.getConfigurationName());
                ExcelExportUtil.safeCopyProperties(existing, item);
            }

        }
        return list;
    }
}
