package com.lanou.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    @Override
    public List<Cost> findAllCost() {
        return costMapper.findAllCost();
    }

    @Override
    public void addCost(Cost cost) {
        costMapper.insert(cost);
    }

    @Override
    public void delCost(Cost cost) {
        costMapper.deleteByPrimaryKey(cost.getCostId());
    }

    @Override
    public void updateCost(Cost cost) {
        costMapper.updateByPrimaryKeySelective(cost);
    }

    @Override
    public PageInfo<Cost> findWithPageInfo(Integer pageNum, Integer pageSize) {
        return queryCostByPage(pageNum, pageSize);
    }

    private PageInfo<Cost> queryCostByPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costList = costMapper.findAllCost();
        System.out.println(costList);
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);
        System.out.println(pageInfo);
        return pageInfo;
    }
}
