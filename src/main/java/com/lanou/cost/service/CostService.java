package com.lanou.cost.service;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.exception.add.AddCostException;

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
     * 通过id查询资费
     *
     * @param costId id
     * @return 资费
     */
    Cost findByCostId(Integer costId);

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

    /**
     * 分页
     *
     * @param pageNum  第几页
     * @param pageSize 一页信息数
     * @return 分页信息
     */
    PageInfo<Cost> findWithPageInfo(Integer pageNum, Integer pageSize);

    /**
     * 判断名字的合法性
     *
     * @param name 名字
     * @return 是否合法
     */
    String judgeName(String name) throws AddCostException;

    /**
     * 判断基本时长的合法性
     *
     * @param cost 基本时长
     * @return 是否合法
     */
    String judgeBaseDuration(Cost cost) throws AddCostException;

    /**
     * 判断基本费用的合法性
     *
     * @param cost 基本费用
     * @return 是否合法
     */
    String judgeBaseCost(Cost cost) throws AddCostException;

    /**
     * 判断单位费用的合法性
     *
     * @param cost 单位费用
     * @return 是否合法
     */
    String judgeUnitCost(Cost cost) throws AddCostException;

    /**
     * 资费说明的合法性
     *
     * @param cost 资费说明
     * @return 是否合法
     */
    String judgeDescr(Cost cost) throws AddCostException;
}
