package com.lanou.role.service;

import com.github.pagehelper.PageInfo;
import com.lanou.role.bean.Role;

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
}
