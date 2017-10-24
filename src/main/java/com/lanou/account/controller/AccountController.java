package com.lanou.account.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.service.AccountService;
import com.lanou.account.service.exception.add.AddAccountException;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.IdCardUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
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

    @RequestMapping(value = "/tomodiaccount")
    public String toModiAccount() {
        return "account/account_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/saveaccount")
    public String saveAccount(HttpServletRequest request, @RequestParam("accountId") Integer accountId) {
        request.getSession().setAttribute("accountId", accountId);
        return null;
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

    @ResponseBody
    @RequestMapping(value = "/addaccount", method = RequestMethod.POST)
    public AjaxResult addAccount(Account account) {
        System.out.println(account);
        account.setStatus("1");
        account.setCreateDate(new Timestamp(System.currentTimeMillis()).toString());
        accountService.addAccount(account);
        return new AjaxResult(account);
    }

    @ResponseBody
    @RequestMapping(value = "/modiaccount")
    public AjaxResult modiAccount(HttpServletRequest request) {
        Integer accountId = (Integer) request.getSession().getAttribute("accountId");
        Account account = accountService.findByAccountId(accountId);
        return new AjaxResult(account);
    }

    // 输入验证
    @ResponseBody
    @RequestMapping(value = "/judgerealname", method = RequestMethod.POST)
    public AjaxResult judgeRealName(Account account) {
        try {
            accountService.judgeRealName(account.getRealName());
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/judgeidcardno", method = RequestMethod.POST)
    public AjaxResult judgeIdCardNo(Account account) throws ParseException {
        try {
            accountService.judgeIdCardNo(account.getIdcardNo());
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        String birthday = IdCardUtils.getBirthday(account.getIdcardNo());
        return new AjaxResult(birthday);
    }

    @ResponseBody
    @RequestMapping(value = "judgeloginname", method = RequestMethod.POST)
    public AjaxResult judgeLoginName(Account account) {
        try {
            accountService.judgeLoginName(account.getLoginName());
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgepwd", method = RequestMethod.POST)
    public AjaxResult judgePwd(@RequestParam("pwd") String pwd) {
        try {
            accountService.judgePwd(pwd);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgepwd2", method = RequestMethod.POST)
    public AjaxResult judgePwd2(@RequestParam("pwd") String pwd, @RequestParam("pwd2") String pwd2) {
        try {
            accountService.judgePwd2(pwd, pwd2);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgetel", method = RequestMethod.POST)
    public AjaxResult judgeTel(@RequestParam("tel") String tel) {
        try {
            accountService.judgeTel(tel);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgeemail", method = RequestMethod.POST)
    public AjaxResult judgeEmail(@RequestParam("email") String email) {
        try {
            accountService.judgeEmail(email);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgeaddress", method = RequestMethod.POST)
    public AjaxResult judgeAddress(@RequestParam("address") String address) {
        try {
            accountService.judgeAddress(address);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgezip", method = RequestMethod.POST)
    public AjaxResult judgeZip(@RequestParam("zip") String zip) {
        try {
            accountService.judgeZip(zip);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgeqq", method = RequestMethod.POST)
    public AjaxResult judgeQQ(@RequestParam("qq") String qq) {
        try {
            accountService.judgeQQ(qq);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }
}
