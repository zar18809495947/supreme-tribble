package com.lanou.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zar on 2017/10/20.
 */
@Controller
public class BaseController {
    @RequestMapping(value = "/")
    public String homepage() {
        return "index";
    }

    @RequestMapping(value = "/feelistpage")
    public String feeListPage() {
        return "fee/fee_list";
    }

    @RequestMapping(value = "/accountlistpage")
    public String accountListPage() {
        return "account/account_list";
    }

    @RequestMapping(value = "/servicepage")
    public String servicePage() {
        return "service/service_list";
    }

    @RequestMapping(value = "rolepage")
    public String rolePage() {
        return "role/role_list";
    }
}
