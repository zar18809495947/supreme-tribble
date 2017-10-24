package com.lanou.service.service;

import com.github.pagehelper.PageInfo;
import com.lanou.service.bean.Service;

import java.util.List;

public interface ServiceService {
    List<Service> findAll();

    /**
     * 模糊查找service信息并分页
     *
     * @param osUsername os用户名
     * @param unixHost   ip地址
     * @param idcardNo   身份证号
     * @param status     状态
     * @param no         第几页
     * @param size       每页几条
     * @return 页面信息
     */
    PageInfo<Service> findServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size);
}
