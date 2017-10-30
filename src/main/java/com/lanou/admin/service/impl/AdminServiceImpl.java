package com.lanou.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.account.service.exception.add.*;
import com.lanou.admin.bean.Admin;
import com.lanou.admin.mapper.AdminMapper;
import com.lanou.admin.service.AdminService;
import com.lanou.role.bean.Module;
import com.lanou.role.bean.Role;
import com.lanou.role.mapper.ModuleMapper;
import com.lanou.utils.RegexUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/27.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private ModuleMapper moduleMapper;

    @Override
    public List<Admin> findAllAdmin() {
        return adminMapper.findAllAdmin();
    }

    @Override
    public List<Module> findAllModule() {
        return moduleMapper.findAllModule();
    }

    @Override
    public PageInfo<Admin> findAdminByPageInfo(String moduleId, String roleName, Integer no, Integer size) {
        return getPageInfo(moduleId, roleName, no, size);
    }

    @Override
    public void addAdmin(Admin admin, List<Role> roleList) {
        adminMapper.addAdmin(admin);
        Integer adminId = admin.getAdminId();
        for (Role role : roleList) {
            adminMapper.addAdminRole(adminId, role.getRoleId());
        }
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        adminMapper.deleteAdmin(adminId);
        adminMapper.deleteAdminRole(adminId);
    }

    @Override
    public void setPassword(List<Admin> adminList) {
        for (Admin admin : adminList) {
            adminMapper.setPassword(admin);
        }
    }

    @Override
    public Admin findByAdminId(Integer adminId) {
        return adminMapper.findByAdminId(adminId);
    }


    private PageInfo<Admin> getPageInfo(String moduleId, String roleName, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Admin> admins = adminMapper.fuzzyFindAdmin(moduleId, roleName);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }

    @Override
    public String judgeRealName(String realName) throws AddAccountException {
        if (realName == null || realName.trim().isEmpty()) {
            throw new RealNameIsEmptyException();
        }
        if (!RegexUtils.isChinaEnglishNum(realName)) {
            throw new RealNameFormatErrorException();
        }
        return null;
    }

    @Override
    public void modiAdmin(Admin admin, List<Role> roleList) {
        adminMapper.updateByPrimaryKeySelective(admin);
        adminMapper.deleteAdminRole(admin.getAdminId());
        for (Role role : roleList) {
            adminMapper.addAdminRole(admin.getAdminId(), role.getRoleId());
        }
    }

    @Override
    public String judgePwd(String pwd) throws AddAccountException {
        if (pwd == null || pwd.trim().isEmpty()) {
            throw new PwdIsEmptyException();
        }
        if (!RegexUtils.isENG_NUM_(pwd)) {
            throw new PwdFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgePwd2(String pwd, String pwd2) throws AddAccountException {
        if (pwd2 == null || pwd2.trim().isEmpty()) {
            throw new Pwd2IsEmptyException();
        }
        if (!RegexUtils.isENG_NUM_(pwd2)) {
            throw new Pwd2FormatErrorException();
        }
        if (!pwd2.equals(pwd)) {
            throw new Pwd2NotIsPwdException();
        }
        return null;
    }

    @Override
    public String judgeTel(String tel) throws AddAccountException {
        if (tel == null || tel.trim().isEmpty()) {
            throw new TelIsEmptyException();
        }
        if (!RegexUtils.isPhone(tel)) {
            throw new TelFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeEmail(String email) throws AddAccountException {
        if (!RegexUtils.isEmail(email)) {
            throw new EmailFormatErrorException();
        }
        return null;
    }
}
