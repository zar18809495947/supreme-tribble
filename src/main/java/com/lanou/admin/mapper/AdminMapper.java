package com.lanou.admin.mapper;

import com.lanou.admin.bean.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zar on 2017/10/27.
 */
public interface AdminMapper {
    /**
     * 查找所有管理员
     *
     * @return 管理员集合
     */
    List<Admin> findAllAdmin();

    /**
     * 高级查询
     *
     * @param moduleId 模块
     * @param roleName 角色
     * @return 管理员集合
     */
    List<Admin> fuzzyFindAdmin(@Param("moduleId") String moduleId, @Param("roleName") String roleName);

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     */
    void addAdmin(Admin admin);

    /**
     * 添加级联关系
     *
     * @param adminId 管理员id
     * @param roleId  角色id
     */
    void addAdminRole(@Param("adminId") Integer adminId,
                      @Param("roleId") Integer roleId);


    /**
     * 删除管理员
     *
     * @param adminId 管理员id
     */
    void deleteAdmin(@Param("adminId") Integer adminId);

    /**
     * 删除级联关系
     *
     * @param adminId 管理员id
     */
    void deleteAdminRole(@Param("adminId") Integer adminId);

    /**
     * 重置管理员密码
     *
     * @param admin 管理员id
     */
    void setPassword(Admin admin);

    /**
     * 通过管理员id查找
     *
     * @param adminId 管理员id
     * @return admin对象
     */
    Admin findByAdminId(@Param("adminId") Integer adminId);

    /**
     * 修改管理员信息
     *
     * @param admin 修改的管理员
     * @return 是否修改成功
     */
    int updateByPrimaryKeySelective(Admin admin);
}
