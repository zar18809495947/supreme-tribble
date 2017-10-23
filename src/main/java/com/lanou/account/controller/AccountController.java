package com.lanou.account.controller;

import com.lanou.account.bean.Account;
import com.lanou.account.service.AccountService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zar on 2017/10/23.
 */
@Controller
public class AccountController {
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/findallaccount")
    public List<AjaxResult> findAllAccount() {
        List<Account> allAccount = accountService.findAllAccount();
        List<AjaxResult> ajaxResults = new ArrayList<>();
        for (Account account : allAccount) {
            ajaxResults.add(new AjaxResult(account));
        }
        return ajaxResults;
    }
}
