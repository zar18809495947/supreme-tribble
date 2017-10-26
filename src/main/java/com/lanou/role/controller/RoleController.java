package com.lanou.role.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;
import com.lanou.role.service.RoleService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/findallrole")
    public AjaxResult findAllRole(@RequestParam("no") Integer no, @RequestParam("size") Integer size) {
        PageInfo<Role> pageInfo = roleService.findRolePageInfo(no, size);
        return new AjaxResult(pageInfo);
    }
}
