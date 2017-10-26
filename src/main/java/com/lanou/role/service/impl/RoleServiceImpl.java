package com.lanou.role.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;
import com.lanou.role.mapper.RoleMapper;
import com.lanou.role.service.RoleService;
import com.lanou.role.service.exception.modi.ModiRoleException;
import com.lanou.role.service.exception.modi.RoleNameFormatException;
import com.lanou.role.service.exception.modi.RoleNameIsEmptyException;
import com.lanou.role.service.exception.modi.RoleNameRepeatException;
import com.lanou.utils.RegexUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findRolePageInfo(Integer no, Integer size) {
        PageInfo<Role> pageInfo = getPageInfo(no, size);
        return pageInfo;
    }

    @Override
    public String judgeRoleName(String roleName) throws ModiRoleException {
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new RoleNameIsEmptyException();
        }
        if (!RegexUtils.Regular(roleName, "^[A-Za-z0-9\u0391-\uFFE5]{1,20}")) {
            throw new RoleNameFormatException();
        }
        List<Role> roleList = roleMapper.findByRoleName(roleName);
        if (roleList.size() != 0) {
            throw new RoleNameRepeatException();
        }
        return null;
    }

    @Override
    public Role findRoleByRoleId(Integer roleId) {
        return roleMapper.findByRoleId(roleId);
    }

    private PageInfo<Role> getPageInfo(Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Role> allRole = roleMapper.findAllRole();
        PageInfo<Role> pageInfo = new PageInfo<>(allRole);
        return pageInfo;
    }
}
