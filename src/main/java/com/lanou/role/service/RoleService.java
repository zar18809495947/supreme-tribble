package com.lanou.role.service;

import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;
import com.lanou.role.service.exception.modi.ModiRoleException;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public interface RoleService {
    /**
     * 查找所有role并分页
     *
     * @param no   第几页
     * @param size 每页容量
     * @return 分页信息
     */
    PageInfo<Role> findRolePageInfo(Integer no, Integer size);

    /**
     * 通过id查找
     *
     * @param roleId id
     * @return 返回role类
     */
    Role findRoleByRoleId(Integer roleId);

    /**
     * 判断roleName合法
     *
     * @param roleName 名字
     * @return 判断结果
     */
    String judgeRoleName(String roleName) throws ModiRoleException;

    /**
     * 更新role
     *
     * @param role role对象
     */
    void updateRole(Role role);

    /**
     * 添加role
     *
     * @param role role类
     */
    void addRole(Role role);

    /**
     * 删除role
     *
     * @param role role类
     */
    void deleteRole(Role role);

    /**
     * 取得所有role
     *
     * @return role集合
     */
    List<Role> getAllRole();
}
