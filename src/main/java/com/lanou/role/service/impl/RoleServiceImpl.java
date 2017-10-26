package com.lanou.role.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;
import com.lanou.role.mapper.RoleMapper;
import com.lanou.role.service.RoleService;
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

    private PageInfo<Role> getPageInfo(Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Role> allRole = roleMapper.findAllRole();
        PageInfo<Role> pageInfo = new PageInfo<>(allRole);
        return pageInfo;
    }
}
