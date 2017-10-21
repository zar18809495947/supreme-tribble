package com.lanou.cost.service.impl;

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
}
