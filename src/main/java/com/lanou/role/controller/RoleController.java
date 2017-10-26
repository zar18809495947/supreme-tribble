package com.lanou.role.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;
import com.lanou.role.service.RoleService;
import com.lanou.role.service.exception.modi.ModiRoleException;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @ResponseBody
    @RequestMapping(value = "/saveroleid")
    public AjaxResult saveRoleId(HttpServletRequest request, Role role) {
        request.getSession().setAttribute("roleId", role.getRoleId());
        return new AjaxResult(null);
    }

    @RequestMapping(value = "/torolemodi")
    public String toRoleModi() {
        return "role/role_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/findrole")
    public AjaxResult findRole(HttpServletRequest request) {
        Integer roleId = (Integer) request.getSession().getAttribute("roleId");
        System.out.println(roleId);
        Role roleByRoleId = roleService.findRoleByRoleId(roleId);
        return new AjaxResult(roleByRoleId);
    }

    @ResponseBody
    @RequestMapping(value = "/judgeRoleName", method = RequestMethod.POST)
    public AjaxResult judgeRoleName(Role role) {
        try {
            roleService.judgeRoleName(role.getRoleName());
        } catch (ModiRoleException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }
}
