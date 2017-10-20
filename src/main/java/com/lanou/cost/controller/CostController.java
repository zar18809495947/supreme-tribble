package com.lanou.cost.controller;

import com.lanou.cost.bean.Cost;
import com.lanou.cost.service.CostService;
import com.lanou.cost.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class CostController {
    @Resource
    private CostService costService;

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
}
