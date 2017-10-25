package com.lanou.service.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.service.ServiceService;
import com.lanou.service.service.exception.start.StartServiceException;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
}
