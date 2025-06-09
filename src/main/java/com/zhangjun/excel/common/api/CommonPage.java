package com.zhangjun.excel.common.api;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author zhangjun
 * @description 分页数据封装类
 * @Date 2025/4/22 16:59
 * @Version 1.0
 */
@Getter
@Setter
public class CommonPage<T>{
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     * @param list
     * @return
     * @param <T>
     */
    public  static <T> CommonPage<T> restPage(List<T> list ){
        CommonPage<T> commonPage = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        commonPage.setTotalPage(pageInfo.getPages());
        commonPage.setPageNum(pageInfo.getPageNum());
        commonPage.setPageSize(pageInfo.getPageSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setList(pageInfo.getList());
        return commonPage;
    }

}
