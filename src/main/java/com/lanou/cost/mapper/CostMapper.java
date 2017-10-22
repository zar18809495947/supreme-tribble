package com.lanou.cost.mapper;

import com.lanou.cost.bean.Cost;

import java.util.List;

public interface CostMapper {
    /**
     * 删除资费
     *
     * @param costId 资费id
     */
    void deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    /**
     * 添加资费
     *
     * @param record 资费
     */
    void insertSelective(Cost record);

    /**
     * 通过id查询资费
     *
     * @param costId 资费id
     * @return 返回资费
     */
    Cost selectByPrimaryKey(Integer costId);

    /**
     * 修改资费
     *
     * @param record 资费
     */
    void updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    /**
     * 查询所有费用
     *
     * @return 费用集合
     */
    List<Cost> findAllCost();
}