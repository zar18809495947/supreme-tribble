package com.lanou.cost.service.impl;

import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zar on 2017/10/20.
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

}
