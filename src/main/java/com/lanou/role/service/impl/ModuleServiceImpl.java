package com.lanou.role.service.impl;

import com.lanou.role.bean.Module;
import com.lanou.role.mapper.ModuleMapper;
import com.lanou.role.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/26.
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> findAllModule() {
        return moduleMapper.findAllModule();
    }
}
