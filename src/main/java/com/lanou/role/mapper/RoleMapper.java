package com.lanou.role.mapper;

import com.lanou.role.bean.Role;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 通过id查找
     *
     * @param roleId id
     * @return role类
     */
    Role findByRoleId(Integer roleId);

    /**
     * 通过角色名查找
     *
     * @param roleName 角色名
     * @return 查找结果
     */
    List<Role> findByRoleName(@Param("roleName") String roleName);
}
