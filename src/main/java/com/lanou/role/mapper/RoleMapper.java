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

    /**
     * 通过角色名更新
     *
     * @param roleName 角色名
     */
    void updateRole(@Param("roleName") String roleName, @Param("roleId") Integer roleId);

    /**
     * 通过id删除
     *
     * @param roleId id
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 添加新对应
     *
     * @param roleId   id
     * @param moduleId id
     */
    void updateRoleModule(@Param("roleId") Integer roleId, @Param("moduleId") Integer moduleId);

    /**
     * 添加role
     *
     * @param role role类
     */
    void insertRole(Role role);

    /**
     * 删除role
     *
     * @param roleId roleID
     */
    void deleteRole(@Param("roleId") Integer roleId);

    /**
     * 通过adminId查找角色集合
     *
     * @param adminId 管理员id
     * @return 角色集合
     */
    List<Role> findAllRoleByAdminId(Integer adminId);
}
