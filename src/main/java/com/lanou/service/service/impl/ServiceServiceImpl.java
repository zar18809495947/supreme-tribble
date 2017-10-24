package com.lanou.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.service.mapper.ServiceMapper;
import com.lanou.service.service.ServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    public ServiceMapper serviceMapper;


    @Override
    public List<com.lanou.service.bean.Service> findAll() {
        return serviceMapper.findAll();
    }

    @Override
    public PageInfo<com.lanou.service.bean.Service> findServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        PageInfo<com.lanou.service.bean.Service> pageInfo = fuzzyFindServicePageInfo(osUsername, unixHost, idcardNo, status, no, size);
        return pageInfo;
    }

    public PageInfo<com.lanou.service.bean.Service> fuzzyFindServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<com.lanou.service.bean.Service> services = serviceMapper.selectServiceFuzzy(osUsername, unixHost, idcardNo, status);
        PageInfo<com.lanou.service.bean.Service> pageInfo = new PageInfo<>(services);
        return pageInfo;
    }
}
