package com.lanou.cost.controller;

import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import com.lanou.cost.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class CostController {
    @Resource
    private CostService costService;

    @RequestMapping(value = "/toaddcost")
    public String toAddCost() {
        return "fee/fee_add";
    }

    @ResponseBody
    @RequestMapping(value = "/allcost")
    public List<AjaxResult> allCost() {
        List<Cost> allCost = costService.findAllCost();
        List<AjaxResult> ajaxResultList = new ArrayList<>();
        for (Cost cost : allCost) {
            ajaxResultList.add(new AjaxResult(cost));
        }
        return ajaxResultList;
    }

    @ResponseBody
    @RequestMapping(value = "/addcost", method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult addCost(Cost cost) {
        cost.setStatus("0");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()));
        System.out.println(cost);
        costService.addCost(cost);
        return new AjaxResult(cost);
    }


    @ResponseBody
    @RequestMapping(value = "/delcost")
    public AjaxResult delCost(Cost cost) {
        System.out.println(cost);
        costService.delCost(cost);
        return new AjaxResult(cost);
    }
}
