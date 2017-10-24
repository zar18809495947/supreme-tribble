package com.lanou.cost.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import com.lanou.cost.service.exception.add.AddCostException;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class CostController {
    @Resource
    private CostService costService;

    @RequestMapping(value = "/toaddcost")
    public String toAddCost(HttpServletRequest request) {
        request.getSession().setAttribute("judge_name", false);
        request.getSession().setAttribute("judge_baseDuration", false);
        request.getSession().setAttribute("judge_baseCost", false);
        request.getSession().setAttribute("judge_unitCost", false);
        request.getSession().setAttribute("judge_descr", false);
        return "fee/fee_add";
    }

    @RequestMapping(value = "/tomodicost")
    public String toModiCost() {
        return "fee/fee_modi";
    }

    @RequestMapping(value = "/todetail")
    public String toDetail() {
        return "fee/fee_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/savecostid")
    public Cost saveCostId(HttpServletRequest request, Cost cost) {
        request.getSession().setAttribute("costId", cost.getCostId());
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
    public AjaxResult addCost(Cost cost, HttpServletRequest request) {
        if ((boolean) request.getSession().getAttribute("judge_name") &&
                (boolean) request.getSession().getAttribute("judge_baseDuration") &&
                (boolean) request.getSession().getAttribute("judge_baseCost") &&
                (boolean) request.getSession().getAttribute("judge_unitCost") &&
                (boolean) request.getSession().getAttribute("judge_descr")) {
            cost.setStatus("0");
            cost.setCreatime(new Timestamp(System.currentTimeMillis()).toString());
            costService.addCost(cost);
            return new AjaxResult(cost);
        }
        return new AjaxResult("无法提交", 1, cost);
    }


    @ResponseBody
    @RequestMapping(value = "/delcost")
    public AjaxResult delCost(Cost cost) {
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
    @RequestMapping(value = "/modifycost", method = RequestMethod.POST)
    public AjaxResult modifyCost(Cost cost) {
        cost.setCreatime(new Timestamp(System.currentTimeMillis()).toString());
        costService.updateCost(cost);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/judgename")
    public AjaxResult judgeName(@RequestParam("name") String name, HttpServletRequest request) {
        String s = null;
        try {
            s = costService.judgeName(name);
        } catch (AddCostException e) {
            request.getSession().setAttribute("judge_name", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_name", true);
        return new AjaxResult(s);
    }

    @ResponseBody
    @RequestMapping(value = "judgebaseduration")
    public AjaxResult judgeBaseDuration(Cost cost, HttpServletRequest request) {
        try {
            costService.judgeBaseDuration(cost);
        } catch (AddCostException e) {
            request.getSession().setAttribute("judge_baseDuration", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_baseDuration", true);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "judgebasecost")
    public AjaxResult judgeBaseCost(Cost cost, HttpServletRequest request) {
        try {
            costService.judgeBaseCost(cost);
        } catch (AddCostException e) {
            request.getSession().setAttribute("judge_baseCost", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_baseCost", true);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "judgeunitcost")
    public AjaxResult judgeUnitCost(Cost cost, HttpServletRequest request) {
        try {
            costService.judgeUnitCost(cost);
        } catch (AddCostException e) {
            request.getSession().setAttribute("judge_unitCost", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_unitCost", true);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/judgedescr")
    public AjaxResult judgeDescr(Cost cost, HttpServletRequest request) {
        try {
            costService.judgeDescr(cost);
        } catch (AddCostException e) {
            request.getSession().setAttribute("judge_descr", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_descr", true);
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/uplistbybasecost")
    public AjaxResult upListByBaseCost(@RequestParam("pageNum") Integer pageNum) {
        PageInfo<Cost> withPageInfo = costService.findWithPageInfo(pageNum, 4);
        List<Cost> list = withPageInfo.getList();
        Collections.sort(list);
        withPageInfo.setList(list);
        return new AjaxResult(withPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/downlistbybasecost")
    public AjaxResult downListByBaseCost(@RequestParam("pageNum") Integer pageNum) {
        PageInfo<Cost> withPageInfo = costService.findWithPageInfo(pageNum, 4);
        List<Cost> list = withPageInfo.getList();
        Comparator comparator = Collections.reverseOrder();
        Collections.sort(list, comparator);
        withPageInfo.setList(list);
        return new AjaxResult(withPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/uplistbybaseduration")
    public AjaxResult upListByBaseDuration(@RequestParam("pageNum") Integer pageNum) {
        PageInfo<Cost> withPageInfo = costService.findWithPageInfo(pageNum, 4);
        List<Cost> list = withPageInfo.getList();
        Collections.sort(list, new Comparator<Cost>() {
                    @Override
                    public int compare(Cost o1, Cost o2) {
                        return Integer.valueOf(o1.getBaseDuration()).compareTo(Integer.valueOf(o2.getBaseDuration()));
                    }
                }
        );
        withPageInfo.setList(list);
        return new AjaxResult(withPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/downlistbybaseduration")
    public AjaxResult downListByBaseDuration(@RequestParam("pageNum") Integer pageNum) {
        PageInfo<Cost> withPageInfo = costService.findWithPageInfo(pageNum, 4);
        List<Cost> list = withPageInfo.getList();
        Collections.sort(list, new Comparator<Cost>() {
                    @Override
                    public int compare(Cost o1, Cost o2) {
                        return Integer.valueOf(o2.getBaseDuration()).compareTo(Integer.valueOf(o1.getBaseDuration()));
                    }
                }
        );
        withPageInfo.setList(list);
        return new AjaxResult(withPageInfo);
    }
}
