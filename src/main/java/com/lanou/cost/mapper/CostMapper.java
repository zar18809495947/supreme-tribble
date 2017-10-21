package com.lanou.cost.mapper;

import com.lanou.cost.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    /**
     * 添加资费
     *
     * @param record 资费
     */
    void insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    /**
     * 查询所有费用
     *
     * @return 费用集合
     */
    List<Cost> findAllCost();
}