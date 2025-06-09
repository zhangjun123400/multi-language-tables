package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.mapper.NetworkConfigurationGuideMapper;
import com.zhangjun.excel.mbg.model.NetworkConfigurationGuide;
import com.zhangjun.excel.processor.SheetProcessor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 22:46
 * @Version 1.0
 */
public class NetworkGuideProcessor  implements SheetProcessor<NetworkConfigurationGuide> {
    @Override
    public List<NetworkConfigurationGuide> processSheet(MultipartFile file, String sheetName, BaseMapper<NetworkConfigurationGuide> baseMapper) {
        List<NetworkConfigurationGuide> list = ExcelExportUtil.readSheet(file, sheetName, NetworkConfigurationGuide.class);

        for (NetworkConfigurationGuide item : list) {
            LambdaQueryWrapper<NetworkConfigurationGuide> query = new LambdaQueryWrapper<>();
            query.eq(NetworkConfigurationGuide::getConfigurationName, item.getConfigurationName())
                    .eq(NetworkConfigurationGuide::getSimplifiedChinese, item.getSimplifiedChinese());

            NetworkConfigurationGuide existing = baseMapper.selectOne(query);
            if (existing != null) {
                existing.setProductModel(item.getProductModel());
                existing.setConfigurationName(item.getConfigurationName());
                ExcelExportUtil.safeCopyProperties(existing, item);
            }
        }
        return list;
    }
}