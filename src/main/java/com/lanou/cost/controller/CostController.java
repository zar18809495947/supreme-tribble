package com.lanou.cost.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import com.lanou.cost.service.exception.add.AddCostException;
import com.lanou.cost.utils.AjaxResult;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/tomodicost")
    public String toModiCost() {
        return "fee/fee_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/savecostid")
    public Cost saveCostId(HttpServletRequest request,Cost cost) {
        request.getSession().setAttribute("costId",cost.getCostId());
        return cost;
    }

    @ResponseBody
    @RequestMapping(value = "/allcost")
    public List<AjaxResult> allCost() {
        List<Cost> allCost = costService.findAllCost();
        List<AjaxResult> ajaxResultList = new ArrayList<AjaxResult>();
        for (Cost cost : allCost) {
            ajaxResultList.add(new AjaxResult(cost));
        }
        return ajaxResultList;
    }

    @ResponseBody
    @RequestMapping(value = "/addcost", method = {RequestMethod.POST, RequestMethod.GET})
    public AjaxResult addCost(Cost cost) {
        cost.setStatus("0");
        cost.setCreatime(new Timestamp(System.currentTimeMillis()).toString());
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

    @ResponseBody
    @RequestMapping(value = "/startcost")
    public AjaxResult startCost(Cost cost) {
        cost.setStartime(new Timestamp(System.currentTimeMillis()).toString());
        costService.updateCost(cost);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/costlistinfo")
    public AjaxResult costListInfo(@RequestParam("no") Integer pageNum,
                                   @RequestParam("size") Integer pageSize) {
        PageInfo<Cost> costPageInfo = costService.findWithPageInfo(pageNum, pageSize);
        return new AjaxResult(costPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/getcost")
    public AjaxResult getCostById(HttpServletRequest request) {
        Integer costId = (Integer) request.getSession().getAttribute("costId");
        Cost cost = costService.findByCostId(costId);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/modifycost",method = RequestMethod.POST)
    public AjaxResult modifyCost(Cost cost) {
        cost.setCreatime(new Timestamp(System.currentTimeMillis()).toString());
        System.out.println(cost);
        costService.updateCost(cost);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/judgename")
    public AjaxResult judgeName(@RequestParam("name") String name) {
        String s = null;
        try {
            s = costService.judgeName(name);
        } catch (AddCostException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(s);
    }
}
