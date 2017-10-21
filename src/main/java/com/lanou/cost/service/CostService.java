package com.lanou.cost.service;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;

import java.util.List;

/**
 * @author zar on 2017/10/20.
 */
public interface CostService {
    /**
     * 查询所有费用
     *
     * @return 费用集合
     */
    List<Cost> findAllCost();

    /**
     * 添加费用
     */
    void addCost(Cost cost);

    /**
     * 删除费用
     *
     * @param cost id
     */
    void delCost(Cost cost);

    /**
     * 修改资费
     *
     * @param cost 资费
     */
    void updateCost(Cost cost);

    PageInfo<Cost> findWithPageInfo(Integer pageNum, Integer pageSize);
}
