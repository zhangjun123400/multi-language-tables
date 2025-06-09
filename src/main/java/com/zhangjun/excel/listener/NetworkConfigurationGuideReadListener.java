package com.zhangjun.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.mapper.BaseConfigurationsMapper;
import com.zhangjun.excel.mbg.mapper.NetworkConfigurationGuideMapper;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import com.zhangjun.excel.mbg.model.NetworkConfigurationGuide;
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
public class NetworkConfigurationGuideReadListener extends AnalysisEventListener<NetworkConfigurationGuide> {

    private final NetworkConfigurationGuideMapper networkConfigurationGuideMapper;

    // 通过构造函数注入 Mapper
    public NetworkConfigurationGuideReadListener(NetworkConfigurationGuideMapper networkConfigurationGuideMapper) {
        this.networkConfigurationGuideMapper = networkConfigurationGuideMapper;
    }
    /**
     * 用于暂时存读取的数据
     */
    private List<NetworkConfigurationGuide> networkConfigurationGuideList = new ArrayList<>();


    /**
     * 每读取一行数据就会调用一次invoke方法
     * @param networkConfigurationGuide
     * @param analysisContext
     */
    @Override
    public void invoke(NetworkConfigurationGuide networkConfigurationGuide, AnalysisContext analysisContext) {
        log.info("读取到一条数据:{}", networkConfigurationGuide);
        networkConfigurationGuideList.add(networkConfigurationGuide);

        // 达到BATCH_COUNT时，需要存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (networkConfigurationGuideList.size() >=5000) {
            saveData();
            //清除内存
            networkConfigurationGuideList.clear();
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
        log.info("{}条数据，开始保存数据库！", networkConfigurationGuideList.size());
        // 这里可以调用持久层完成数据入库
        for (NetworkConfigurationGuide item : networkConfigurationGuideList) {

            LambdaQueryWrapper<NetworkConfigurationGuide> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(NetworkConfigurationGuide::getConfigurationName, item.getConfigurationName())
                    .eq(NetworkConfigurationGuide::getSimplifiedChinese, item.getSimplifiedChinese());

            NetworkConfigurationGuide existing =networkConfigurationGuideMapper.selectOne(queryWrapper);
            if (existing != null) {
                ExcelExportUtil.safeCopyProperties(item,existing);
                networkConfigurationGuideMapper.updateById(existing);
            }
            else {
                networkConfigurationGuideMapper.insert(item);
            }
        }

        log.info("存储数据库成功！");
    }
}
