package com.lanou.admin.service;

import com.github.pagehelper.PageInfo;
import com.lanou.admin.bean.Admin;
import com.lanou.role.bean.Module;

import java.util.List;

/**
 * @author zar on 2017/10/27.
 */
public interface AdminService {
    /**
     * 查找所有管理员集合
     *
     * @return 管理员集合
     */
    List<Admin> findAllAdmin();

    /**
     * 查找所有权限
     *
     * @return 权限集合
     */
    List<Module> findAllModule();

    /**
     * 管理员查询分页
     *
     * @param moduleId 模块
     * @param roleName 角色
     * @return 管理员集合
     */
    PageInfo<Admin> findAdminByPageInfo(Integer moduleId, String roleName, Integer no, Integer size);
}
