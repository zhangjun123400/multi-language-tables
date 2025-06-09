package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import com.zhangjun.excel.mbg.model.MessagePush;
import com.zhangjun.excel.processor.SheetProcessor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 23:04
 * @Version 1.0
 */
public class MessagePushProcessor implements SheetProcessor<MessagePush> {
    @Override
    public List<MessagePush> processSheet(MultipartFile file, String sheetName,  BaseMapper<MessagePush> baseMapper) {
        List<MessagePush> list = ExcelExportUtil.readSheet(file, sheetName, MessagePush.class);

        for (MessagePush item : list) {

            LambdaQueryWrapper<MessagePush> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MessagePush::getConfigurationName, item.getConfigurationName())
                    .eq(MessagePush::getSimplifiedChinese, item.getSimplifiedChinese());

            MessagePush existing = baseMapper.selectOne(queryWrapper);
            if (existing != null) {
                existing.setMessageId(item.getMessageId());
                existing.setConfigurationId(item.getConfigurationId());
                existing.setConfigurationName(item.getConfigurationName());
                ExcelExportUtil.safeCopyProperties(existing, item);
            }
        }
        return list;
    }
}