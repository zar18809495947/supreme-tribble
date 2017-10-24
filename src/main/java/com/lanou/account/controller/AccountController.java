package com.lanou.account.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.service.AccountService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zar on 2017/10/23.
 */
@Controller
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/toaddaccount")
    public String toAddAccount() {
        return "account/account_add";
    }

    @ResponseBody
    @RequestMapping(value = "/stopaccount")
    public AjaxResult stopAccount(Account account) {
        if ("1".equals(account.getStatus())) {
            System.out.println(1);
            account.setCreateDate(new Timestamp(System.currentTimeMillis()).toString());
        } else if ("2".equals(account.getStatus())) {
            System.out.println(2);
            account.setPauseDate(new Timestamp(System.currentTimeMillis()).toString());
        } else {
            account.setCloseDate(new Timestamp(System.currentTimeMillis()).toString());
        }
        accountService.stopAccount(account);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/findaccount")
    public AjaxResult findAccount(Account account, @RequestParam("no") Integer no, @RequestParam("size") Integer size) {
        if (account.getStatus() == null || account.getLoginName().trim().isEmpty()) {
            account.setLoginName(null);
        }
        if (account.getStatus() == null || account.getRealName().trim().isEmpty()) {
            account.setRealName(null);
        }
        if (account.getStatus() == null || account.getIdcardNo().trim().isEmpty()) {
            account.setRealName(null);
        }
        if (account.getStatus() == null || account.getStatus().trim().isEmpty()) {
            account.setStatus(null);
        }
        PageInfo<Account> fuzzy = accountService.findByFuzzy(account, no, size);
        return new AjaxResult(fuzzy);
    }
}
