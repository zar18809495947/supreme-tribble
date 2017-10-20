package com.lanou.cost.controller;

import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class CostController {
    @Resource
    private CostService costService;
}
