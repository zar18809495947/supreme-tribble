package com.lanou.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.mapper.ServiceMapper;
import com.lanou.service.service.ServiceService;
import com.lanou.service.service.exception.start.AccountStatusIsNotStartException;
import com.lanou.service.service.exception.start.StartServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    public ServiceMapper serviceMapper;


    @Override
    public List<Servicezz> findAll() {
        return serviceMapper.findAll();
    }

    @Override
    public PageInfo<Servicezz> findServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        PageInfo<Servicezz> pageInfo = fuzzyFindServicePageInfo(osUsername, unixHost, idcardNo, status, no, size);
        return pageInfo;
    }

    @Override
    public void updateService(Servicezz servicezz) throws StartServiceException{
        if ("1".equals(servicezz.getStatus())) {
            Servicezz service1 = serviceMapper.selectByPrimaryKey(servicezz.getServiceId());
            if (!"1".equals(service1.getAccount().getStatus())) {
                throw new AccountStatusIsNotStartException();
            }
            servicezz.setPauseDate("0000-00-00");
        } else if ("2".equals(servicezz.getStatus())) {
            servicezz.setPauseDate(new Timestamp(System.currentTimeMillis()).toString());
        } else {
            servicezz.setCloseDate(new Timestamp(System.currentTimeMillis()).toString());
        }
        serviceMapper.updateByPrimaryKeySelective(servicezz);
    }

    @Override
    public void addService(Servicezz servicezz) {
        serviceMapper.insertSelective(servicezz);
    }

    public PageInfo<Servicezz> fuzzyFindServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Servicezz> services = serviceMapper.selectServiceFuzzy(osUsername, unixHost, idcardNo, status);
        PageInfo<Servicezz> pageInfo = new PageInfo<>(services);
        return pageInfo;
    }
}
