package com.lanou.admin.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.admin.bean.Admin;
import com.lanou.admin.service.AdminService;
import com.lanou.role.bean.Module;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/27.
 */
@Controller
public class AdminController {
    @Resource
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/findalladmin", method = RequestMethod.POST)
    public AjaxResult findAllAdmin(@RequestParam("moduleId") String moduleId,
                                   @RequestParam("roleName") String roleName,
                                   @RequestParam("no") Integer no,
                                   @RequestParam("size") Integer size) {
        if (roleName == null || roleName.trim().isEmpty()) {
            roleName = null;
        }
        if (moduleId == null || moduleId.trim().isEmpty() || moduleId.equals("0")) {
            moduleId = null;
        }
        PageInfo<Admin> adminByPageInfo = adminService.findAdminByPageInfo(moduleId, roleName, no, size);
        return new AjaxResult(adminByPageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/adminfindallmodule")
    public AjaxResult findAllModule() {
        List<Module> allModule = adminService.findAllModule();
        return new AjaxResult(allModule);
    }
}
