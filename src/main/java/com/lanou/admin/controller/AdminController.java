package com.lanou.admin.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.account.service.exception.add.AddAccountException;
import com.lanou.admin.bean.Admin;
import com.lanou.admin.service.AdminService;
import com.lanou.role.bean.Module;
import com.lanou.role.bean.Role;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zar on 2017/10/27.
 */
@Controller
public class AdminController {
    @Resource
    private AdminService adminService;

    @RequestMapping("/toaddadmin")
    public String toAddAdmin() {
        return "admin/admin_add";
    }

    @ResponseBody
    @RequestMapping(value = "/saveadminid")
    public String saveAdminId(HttpServletRequest request, Admin admin) {
        request.getSession().setAttribute("adminId", admin.getAdminId());
        return null;
    }

    @RequestMapping(value = "/tosaveadmin")
    public String toSaveAdmin() {
        return "admin/admin_modi";
    }

    @ResponseBody
    @RequestMapping(value = "/getadmin")
    public AjaxResult getAdmin(HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
        Admin byAdminId = adminService.findByAdminId(adminId);
        return new AjaxResult(byAdminId);
    }

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

    @ResponseBody
    @RequestMapping(value = "/addadmin", method = RequestMethod.POST)
    public AjaxResult addAdmin(Admin admin, @RequestParam("roleName") String roleName) {
        String[] split = roleName.split(",");
        List<Role> roleList = new ArrayList<>();
        for (String s : split) {
            Role role = new Role();
            role.setRoleId(Integer.valueOf(s));
            roleList.add(role);
        }
        adminService.addAdmin(admin, roleList);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/modiadminname", method = RequestMethod.POST)
    public AjaxResult modiAdmin(Admin admin, @RequestParam("roleName") String roleName, HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
        admin.setAdminId(adminId);
        String[] split = roleName.split(",");
        List<Role> roleList = new ArrayList<>();
        for (String s : split) {
            Role role = new Role();
            role.setRoleId(Integer.valueOf(s));
            roleList.add(role);
        }
        adminService.modiAdmin(admin, roleList);
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteadmin", method = RequestMethod.POST)
    public AjaxResult deleteAdmin(Admin admin) {
        adminService.deleteAdmin(admin.getAdminId());
        return new AjaxResult(admin);
    }

    @ResponseBody
    @RequestMapping(value = "/modipassword")
    public AjaxResult modiPassword(@RequestParam("str") String str) {
        String[] split = str.split(",");
        List<Admin> adminList = new ArrayList<>();
        for (String s : split) {
            Admin admin = new Admin();
            admin.setAdminId(Integer.valueOf(s));
            adminList.add(admin);
        }
        adminService.setPassword(adminList);
        return new AjaxResult(null);
    }

    // 输入验证
    @ResponseBody
    @RequestMapping(value = "/fjudgerealname", method = RequestMethod.POST)
    public AjaxResult judgeRealName(Admin admin) {
        try {
            adminService.judgeRealName(admin.getName());
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/fjudgepwd", method = RequestMethod.POST)
    public AjaxResult judgePwd(@RequestParam("pwd") String pwd) {
        try {
            adminService.judgePwd(pwd);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/fjudgepwd2", method = RequestMethod.POST)
    public AjaxResult judgePwd2(@RequestParam("pwd") String pwd, @RequestParam("pwd2") String pwd2) {
        try {
            adminService.judgePwd2(pwd, pwd2);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/fjudgetel", method = RequestMethod.POST)
    public AjaxResult judgeTel(@RequestParam("tel") String tel) {
        try {
            adminService.judgeTel(tel);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }

    @ResponseBody
    @RequestMapping(value = "/fjudgeemail", method = RequestMethod.POST)
    public AjaxResult judgeEmail(@RequestParam("email") String email) {
        try {
            adminService.judgeEmail(email);
        } catch (AddAccountException e) {
            return new AjaxResult(e.getMessage(), 1, null);
        }
        return new AjaxResult(null);
    }
}
