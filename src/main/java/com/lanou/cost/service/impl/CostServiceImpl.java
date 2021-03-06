package com.lanou.cost.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import com.lanou.cost.service.exception.add.*;
import com.lanou.cost.service.exception.del.CostDeleteException;
import com.lanou.cost.service.exception.del.ServiceExistException;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.mapper.ServiceMapper;
import org.junit.Test;
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

    @Resource
    private ServiceMapper serviceMapper;

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
    public void delCost(Cost cost) throws CostDeleteException {
        List<Servicezz> byCostId = serviceMapper.findByCostId(cost.getCostId());
        if (byCostId.size() != 0) {
            throw new ServiceExistException();
        }
        costMapper.deleteByPrimaryKey(cost.getCostId());
    }

    @Override
    public void updateCost(Cost cost) {
        costMapper.updateByPrimaryKeySelective(cost);
    }

    @Override
    public PageInfo<Cost> findWithPageInfo(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costList = costMapper.findAllCost();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);
        return pageInfo;
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
        if (!judgeRegex("^[.A-Za-z0-9\u0391-\uFFE5]{1,50}", name)) {
            throw new NameFormatErrorException();
        }
        return "可以使用";
    }

    @Override
    public String judgeModiName(String name) throws AddCostException {
        if (!(name != null && !name.trim().isEmpty())) {
            throw new NameIsEmptyException();
        }
        if (!judgeRegex("^[.A-Za-z0-9\u0391-\uFFE5]{1,50}", name)) {
            throw new NameFormatErrorException();
        }
        return "可以使用";
    }

    @Override
    public String judgeBaseDuration(Cost cost) throws AddCostException {
        if ("1".equals(cost.getCostType())) {
            if (!cost.getBaseDuration().trim().isEmpty()) {
                throw new BaseDurationNotMatchException();
            }
        } else if ("2".equals(cost.getCostType())) {
            if (cost.getBaseDuration().trim().isEmpty()) {
                throw new BaseDurationIsEmptyException();
            }
            System.out.println(cost.getBaseDuration());
            if (!judgeRegex("[0-5][0-9][0-9]|[0-9]{1,2}|[6][0][0]", cost.getBaseDuration())) {
                throw new BaseDurationFormatErrorException();
            }
        } else {
            if (!cost.getBaseDuration().trim().isEmpty()) {
                throw new BaseDurationNotMatchException();
            }
        }
        return null;
    }

    @Override
    public String judgeBaseCost(Cost cost) throws AddCostException {
        if ("1".equals(cost.getCostType())) {
            if (cost.getBaseCost().trim().isEmpty()) {
                throw new BaseCostIsEmptyException();
            }
            System.out.println(cost.getBaseCost());
            if (!judgeRegex("^[0-9]+(.[0-9]{1,2})?$", cost.getBaseCost())) {
                throw new BaseCostFormatErrorException();
            }
        } else if ("2".equals(cost.getCostType())) {
            if (cost.getBaseCost().trim().isEmpty()) {
                throw new BaseCostIsEmptyException();
            }
            System.out.println(cost.getBaseCost());
            if (!judgeRegex("^[0-9]+(.[0-9]{1,2})?$", cost.getBaseCost())) {
                throw new BaseCostFormatErrorException();
            }
        } else {
            if (!cost.getBaseCost().trim().isEmpty()) {
                throw new BaseCostNotMatchException();
            }
        }
        return null;
    }

    @Override
    public String judgeUnitCost(Cost cost) throws AddCostException {
        if ("1".equals(cost.getCostType())) {
            if (!cost.getUnitCost().trim().isEmpty()) {
                throw new UnitCostNotMatchException();
            }
        } else if ("2".equals(cost.getCostType())) {
            if (cost.getUnitCost().trim().isEmpty()) {
                throw new UnitCostIsEmptyException();
            }
            System.out.println(cost.getUnitCost());
            if (!judgeRegex("^[0-9]+(.[0-9]{1,2})?$", cost.getUnitCost())) {
                throw new UnitCostFormatErrorException();
            }
        } else {
            if (cost.getUnitCost().trim().isEmpty()) {
                throw new UnitCostIsEmptyException();
            }
            System.out.println(cost.getUnitCost());
            if (!judgeRegex("^[0-9]+(.[0-9]{1,2})?$", cost.getUnitCost())) {
                throw new UnitCostFormatErrorException();
            }
        }
        return null;
    }

    @Override
    public String judgeDescr(Cost cost) throws AddCostException {
        if (cost.getDescr().trim().isEmpty()) {
            throw new DescrIsEmptyException();
        }
        if (!judgeRegex("^[./,A-Za-z0-9\u0391-\uFFE5]{1,100}", cost.getDescr())) {
            throw new DescrFormatErrorException();
        }
        return "可以使用";
    }

    @Override
    public PageInfo<Cost> upSortByBaseCost(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costs = costMapper.upSortByBaseCost();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);
        return pageInfo;
    }

    @Override
    public PageInfo<Cost> downSortByBaseCost(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costs = costMapper.downSortByBaseCost();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);
        return pageInfo;
    }

    @Override
    public PageInfo<Cost> upSortByBaseDuration(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costs = costMapper.upSortByBaseDuration();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);
        return pageInfo;
    }

    @Override
    public PageInfo<Cost> downSortByBaseDuration(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Cost> costs = costMapper.downSortByBaseDuration();
        PageInfo<Cost> pageInfo = new PageInfo<Cost>(costs);
        return pageInfo;
    }

    private boolean judgeRegex(String reg, String target) {
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(target);
        return matcher.matches();
    }

//    @Test
//    public void test() {
//
//            boolean b = judgeRegex("^[0-9]+(.[0-9]{1,2})?$", String.valueOf(10.555));
//            System.out.println(b);
//
//    }
}
