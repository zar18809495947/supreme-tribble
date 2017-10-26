package com.lanou.role.service;

import com.lanou.role.bean.Module;

import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
public interface ModuleService {
    /**
     * 查找所有module
     *
     * @return module集合
     */
    List<Module> findAllModule();
}
