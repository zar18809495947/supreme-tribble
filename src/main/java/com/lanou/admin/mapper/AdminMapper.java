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
    List<Admin> fuzzyFindAdmin(@Param("moduleId") Integer moduleId, @Param("roleName") String roleName);
}
