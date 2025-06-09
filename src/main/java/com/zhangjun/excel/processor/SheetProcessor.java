package com.zhangjun.excel.processor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.mbg.mapper.BaseConfigurationsMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 22:38
 * @Version 1.0
 */
public interface SheetProcessor<T> {
    List<T> processSheet(MultipartFile file, String sheetName, BaseMapper<T> baseMapper);
}
