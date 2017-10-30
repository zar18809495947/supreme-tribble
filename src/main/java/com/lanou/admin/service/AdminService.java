package com.lanou.admin.service;

import com.github.pagehelper.PageInfo;
import com.lanou.account.service.exception.add.AddAccountException;
import com.lanou.admin.bean.Admin;
import com.lanou.role.bean.Module;
import com.lanou.role.bean.Role;

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
    PageInfo<Admin> findAdminByPageInfo(String moduleId, String roleName, Integer no, Integer size);

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     */
    void addAdmin(Admin admin, List<Role> roleList);

    /**
     * 删除管理员
     *
     * @param adminId 管理员id
     */
    void deleteAdmin(Integer adminId);

    /**
     * 重置密码
     *
     * @param adminList 重置的管理员
     */
    void setPassword(List<Admin> adminList);

    /**
     * 通过id查找管理员
     *
     * @param adminId 管理员id
     * @return 管理员对象
     */
    Admin findByAdminId(Integer adminId);

    /**
     * 密码验证
     *
     * @param pwd 密码
     * @return 判断结果
     */
    String judgePwd(String pwd) throws AddAccountException;

    /**
     * 密码重复验证
     *
     * @param pwd  密码
     * @param pwd2 重复密码
     * @return 判断结果
     */
    String judgePwd2(String pwd, String pwd2) throws AddAccountException;

    /**
     * 电话验证
     *
     * @param tel 电话
     * @return 判断结果
     */
    String judgeTel(String tel) throws AddAccountException;

    /**
     * 邮件验证
     *
     * @param email 邮件
     * @return 判断结果
     */
    String judgeEmail(String email) throws AddAccountException;

    /**
     * 实名验证
     *
     * @param realName 实名
     * @return 判断结果
     */
    String judgeRealName(String realName) throws AddAccountException;

    /**
     * 修改管理员
     *
     * @param admin 管理员信息
     */
    void modiAdmin(Admin admin, List<Role> roleList);
}
