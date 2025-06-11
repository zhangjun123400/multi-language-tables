package com.zhangjun.excel.processor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.mapper.NetworkConfigurationGuideMapper;
import com.zhangjun.excel.mbg.model.NetworkConfigurationGuide;
import com.zhangjun.excel.processor.SheetProcessor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
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
            /**
            Field[] fields = item.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);  // 允许访问私有属性
                String name = field.getName();
                try {
                    Object value = field.get(item);//获取属性值

                    if (value != null) {
                        if(item.getConfigurationName().contains("引导标题"))
                        {
                            if (value.toString().length()>80)
                            {

                                System.out.println("===========");
                                System.out.println(name + ": " + value);
                                System.out.println("===========");
                            }
                        }else {
                            if (value.toString().length()>250)
                            {
                                value=value.toString().substring(0,200);
                                System.out.println("===========");
                                System.out.println(name + ": " + value);
                                System.out.println("===========");
                            }
                        }

                    }



                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
             */
        }

        return list;
    }
}