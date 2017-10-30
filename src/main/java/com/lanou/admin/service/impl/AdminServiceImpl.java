package com.lanou.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.admin.bean.Admin;
import com.lanou.admin.mapper.AdminMapper;
import com.lanou.admin.service.AdminService;
import com.lanou.role.bean.Module;
import com.lanou.role.mapper.ModuleMapper;
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


    private PageInfo<Admin> getPageInfo(String moduleId, String roleName, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Admin> admins = adminMapper.fuzzyFindAdmin(moduleId, roleName);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }
}
