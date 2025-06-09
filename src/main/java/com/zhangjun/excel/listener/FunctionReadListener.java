package com.zhangjun.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangjun.excel.common.util.ExcelExportUtil;
import com.zhangjun.excel.mbg.mapper.BaseConfigurationsMapper;
import com.zhangjun.excel.mbg.mapper.FunctionDefinitionMapper;
import com.zhangjun.excel.mbg.model.BaseConfigurations;
import com.zhangjun.excel.mbg.model.FunctionDefinition;
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
public class FunctionReadListener extends AnalysisEventListener<FunctionDefinition> {

    private final FunctionDefinitionMapper functionDefinitionMapper;

    // 通过构造函数注入 Mapper
    public FunctionReadListener(FunctionDefinitionMapper functionDefinitionMapper) {
        this.functionDefinitionMapper = functionDefinitionMapper;
    }
    /**
     * 用于暂时存读取的数据
     */
    private List<FunctionDefinition> functionDefinitionList = new ArrayList<>();


    /**
     * 每读取一行数据就会调用一次invoke方法
     * @param functionDefinition
     * @param analysisContext
     */
    @Override
    public void invoke(FunctionDefinition functionDefinition, AnalysisContext analysisContext) {
        log.info("读取到一条数据:{}", functionDefinition);
        functionDefinitionList.add(functionDefinition);

        // 达到BATCH_COUNT时，需要存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (functionDefinitionList.size() >=5000) {
            saveData();
            //清除内存
            functionDefinitionList.clear();
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
        log.info("{}条数据，开始保存数据库！", functionDefinitionList.size());
        // 这里可以调用持久层完成数据入库
        for (FunctionDefinition item : functionDefinitionList) {

            LambdaQueryWrapper<FunctionDefinition> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FunctionDefinition::getConfigurationName,item.getConfigurationName())
                    .eq(FunctionDefinition::getSimplifiedChinese,item.getSimplifiedChinese());
            FunctionDefinition existing =functionDefinitionMapper.selectOne(queryWrapper);
            if (existing != null) {
                ExcelExportUtil.safeCopyProperties(item,existing);
                functionDefinitionMapper.updateById(existing);
            }
            else {
                functionDefinitionMapper.insert(item);
            }
        }

        log.info("存储数据库成功！");
    }
}
