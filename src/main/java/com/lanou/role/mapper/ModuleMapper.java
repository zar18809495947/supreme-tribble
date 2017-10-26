package com.lanou.role.mapper;

import com.lanou.role.bean.Module;
import com.lanou.role.bean.Role;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public interface ModuleMapper {

    /**
     * 查找所有Module
     *
     * @return module集合
     */
    List<Module> findAllModule();

    /**
     * 通过roleId查找所有module
     *
     * @param roleId id
     * @return module集合
     */
    List<Module> findByRoleId(Integer roleId);
}
