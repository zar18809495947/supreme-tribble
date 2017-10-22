package com.lanou.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import com.lanou.cost.service.exception.add.AddCostException;
import com.lanou.cost.service.exception.add.NameFormatErrorException;
import com.lanou.cost.service.exception.add.NameIsEmptyException;
import com.lanou.cost.service.exception.add.NameRepeatException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Cost findByCostId(Integer costId) {
        return costMapper.selectByPrimaryKey(costId);
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

    @Override
    public String judgeName(String name) throws AddCostException {
        if (!(name != null && !name.trim().isEmpty())) {
            throw new NameIsEmptyException();
        }
        List<Cost> byName = costMapper.findByName(name);
        System.out.println(byName);
        if (!byName.isEmpty()) {
            throw new NameRepeatException();
        }
        String reg = "^[a-zA-Z0-9_u4e00-u9fa5]+$";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(name);
        if (!matcher.matches()) {
            throw new NameFormatErrorException();
        }
        return "可以使用";
    }

    private PageInfo<Cost> queryCostByPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costList = costMapper.findAllCost();
//        System.out.println(costList);
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);
//        System.out.println(pageInfo);
        return pageInfo;
    }
}
