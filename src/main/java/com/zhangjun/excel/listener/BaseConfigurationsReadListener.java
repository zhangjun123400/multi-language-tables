package com.zhangjun.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.mapper.BaseConfigurationsMapper;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/7 16:00
 * @Version 1.0
 */
@Slf4j
@Getter
public class BaseConfigurationsReadListener extends AnalysisEventListener<BaseConfigurations> {

    private final BaseConfigurationsMapper baseConfigurationsMapper;

    // 通过构造函数注入 Mapper
    public BaseConfigurationsReadListener(BaseConfigurationsMapper baseConfigurationsMapper) {
        this.baseConfigurationsMapper = baseConfigurationsMapper;
    }
    /**
     * 用于暂时存读取的数据
     */
    private List<BaseConfigurations> baseConfigurationsList = new ArrayList<>();


    /**
     * 每读取一行数据就会调用一次invoke方法
     * @param baseConfigurations
     * @param analysisContext
     */
    @Override
    public void invoke(BaseConfigurations baseConfigurations, AnalysisContext analysisContext) {
        log.info("读取到一条数据:{}", baseConfigurations);
        baseConfigurationsList.add(baseConfigurations);

        // 达到BATCH_COUNT时，需要存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (baseConfigurationsList.size() >=5000) {
            saveData();
            //清除内存
            baseConfigurationsList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 确保最后一批数据被保存
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 保存数据，这里只是打印，实际应用中可以将数据存入数据库
     */
    private void saveData() {
        log.info("{}条数据，开始保存数据库！", baseConfigurationsList.size());
        // 这里可以调用持久层完成数据入库
        for (BaseConfigurations item : baseConfigurationsList) {

            LambdaQueryWrapper<BaseConfigurations> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(BaseConfigurations::getSimplifiedChinese, item.getSimplifiedChinese());

            BaseConfigurations existing =baseConfigurationsMapper.selectOne(queryWrapper);
            if (existing != null) {
                ExcelExportUtil.safeCopyProperties(item,existing);
                baseConfigurationsMapper.updateById(existing);
            }
            else {
                baseConfigurationsMapper.insert(item);
            }
        }

        log.info("存储数据库成功！");
    }
}
