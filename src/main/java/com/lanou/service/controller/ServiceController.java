package com.lanou.service.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.cost.bean.Cost;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.service.ServiceService;
import com.lanou.service.service.exception.add.AddServiceException;
import com.lanou.service.service.exception.start.StartServiceException;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ServiceController {
    @Resource
    private ServiceService serviceService;

    @ResponseBody
    @RequestMapping(value = "/findallservice")
    public AjaxResult findAllService() {
        List<Servicezz> serviceAll = serviceService.findAll();
        return new AjaxResult(serviceAll);
    }

    @RequestMapping(value = "/toaddservice")
    public String toAddService(HttpServletRequest request) {
        request.getSession().setAttribute("judge_accountId", false);
        request.getSession().setAttribute("judge_unixHost", false);
        request.getSession().setAttribute("judge_osUsername", false);
        request.getSession().setAttribute("judge_loginPasswd", false);
        request.getSession().setAttribute("judge_loginPasswd2", false);
        return "service/service_add";
    }

    @RequestMapping(value = "/tomodiservice")
    public String toModiService() {
        return "service/service_modi";
    }

    @RequestMapping(value = "/todetailservice")
    public String toDetail() {
        return "service/service_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/saveserviceid")
    public String saveServiceId(HttpServletRequest request, Servicezz servicezz) {
        System.out.println(servicezz);
        request.getSession().setAttribute("serviceId", servicezz.getServiceId());
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/findpageinfo")
    public AjaxResult findServicePageInfo(@RequestParam("osUsername") String osUsername,
                                          @RequestParam("unixHost") String unixHost,
                                          @RequestParam("idcardNo") String idcardNo,
                                          @RequestParam("status") String status,
                                          @RequestParam("no") Integer no,
                                          @RequestParam("size") Integer size) {
        if (osUsername == null || osUsername.trim().isEmpty()) {
            osUsername = null;
        }
        if (unixHost == null || unixHost.trim().isEmpty()) {
            unixHost = null;
        }
        if (idcardNo == null || idcardNo.trim().isEmpty()) {
            idcardNo = null;
        }
        if ("0".equals(status)) {
            status = null;
        }
        PageInfo<Servicezz> services = serviceService.findServicePageInfo(osUsername, unixHost, idcardNo, status, no, size);
        return new AjaxResult(services);
    }

    @ResponseBody
    @RequestMapping(value = "/delservice")
    public AjaxResult delService(Servicezz servicezz) {
        try {
            serviceService.updateService(servicezz);
        } catch (StartServiceException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(servicezz);
    }

    @ResponseBody
    @RequestMapping(value = "/addservice", method = RequestMethod.POST)
    public AjaxResult addService(Servicezz servicezz, HttpServletRequest request) {
        if ((Boolean) request.getSession().getAttribute("judge_accountId") &&
                (Boolean) request.getSession().getAttribute("judge_unixHost") &&
                (Boolean) request.getSession().getAttribute("judge_osUsername") &&
                (Boolean) request.getSession().getAttribute("judge_loginPasswd") &&
                (Boolean) request.getSession().getAttribute("judge_loginPasswd2")) {
            servicezz.setStatus("1");
            servicezz.setCreateDate(new Timestamp(System.currentTimeMillis()).toString());
            serviceService.addService(servicezz);
            return new AjaxResult(servicezz);
        }
        return new AjaxResult("保存失败！请按照提示正确书写", 1, null);
    }

    @ResponseBody
    @RequestMapping(value = "/findidcard")
    public AjaxResult findIdCard(@RequestParam("idcardNo") String idcardNo) {
        List<Account> idCard = null;
        try {
            idCard = serviceService.findIdCard(idcardNo);
        } catch (AddServiceException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(idCard);
    }

    @ResponseBody
    @RequestMapping(value = "/findaccountid")
    public AjaxResult findAccountId(@RequestParam("accountId") Integer accountId, HttpServletRequest request) {
        try {
            serviceService.findByAccountId(accountId);
        } catch (AddServiceException e) {
            request.getSession().setAttribute("judge_accountId", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_accountId", true);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/findcost")
    public AjaxResult findCost() {
        List<Cost> cost = serviceService.findCost();
        return new AjaxResult(cost);
    }

    @ResponseBody
    @RequestMapping(value = "/judgeip")
    public AjaxResult judgeIP(Servicezz servicezz, HttpServletRequest request) {
        try {
            serviceService.judgeIP(servicezz.getUnixHost());
        } catch (AddServiceException e) {
            request.getSession().setAttribute("judge_unixHost", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_unixHost", true);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/judgeosusername")
    public AjaxResult judgeOSUsername(Servicezz servicezz, HttpServletRequest request) {
        try {
            serviceService.judgeOsUsername(servicezz.getOsUsername());
        } catch (AddServiceException e) {
            request.getSession().setAttribute("judge_osUsername", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_osUsername", true);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgeservicepwd", method = RequestMethod.POST)
    public AjaxResult judgePwd(@RequestParam("pwd") String pwd, HttpServletRequest request) {
        try {
            serviceService.judgePwd(pwd);
        } catch (AddServiceException e) {
            request.getSession().setAttribute("judge_loginPasswd", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_loginPasswd", true);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "judgeservicepwd2", method = RequestMethod.POST)
    public AjaxResult judgePwd2(@RequestParam("pwd") String pwd, @RequestParam("pwd2") String pwd2, HttpServletRequest request) {
        try {
            serviceService.judgePwd2(pwd, pwd2);
        } catch (AddServiceException e) {
            request.getSession().setAttribute("judge_loginPasswd2", false);
            return new AjaxResult(e.getMessage(), 1, null);
        }
        request.getSession().setAttribute("judge_loginPasswd2", true);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/findservice")
    public AjaxResult findService(HttpServletRequest request) {
        Integer serviceId = (Integer) request.getSession().getAttribute("serviceId");
        Servicezz servicezz = serviceService.findByServiceId(serviceId);
        return new AjaxResult(servicezz);
    }
}
