package com.lanou.role.mapper;

import com.lanou.role.bean.Role;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public interface RoleMapper {
    /**
     * 查找所有role
     *
     * @return role集合
     */
    List<Role> findAllRole();

    /**
     * 通过moduleId查找role集合
     *
     * @param moduleId id
     * @return role集合
     */
    List<Role> findByModuleId(Integer moduleId);
}
