package com.lanou.cost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }
}
