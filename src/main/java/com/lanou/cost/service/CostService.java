package com.lanou.cost.service;

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
}
