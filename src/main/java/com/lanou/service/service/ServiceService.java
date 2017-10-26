package com.lanou.service.service;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.cost.bean.Cost;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.service.exception.add.AddServiceException;
import com.lanou.service.service.exception.start.StartServiceException;

import java.util.List;

public interface ServiceService {
    List<Servicezz> findAll();

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
    PageInfo<Servicezz> findServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size);

    /**
     * 更新Service
     *
     * @param servicezz 更新信息
     */
    void updateService(Servicezz servicezz) throws StartServiceException;

    /**
     * 添加Service
     *
     * @param servicezz 添加的信息
     */
    void addService(Servicezz servicezz);

    /**
     * 查找身份证号
     *
     * @param idcard 身份证号
     * @return 是否找到
     */
    List<Account> findIdCard(String idcard) throws AddServiceException;

    /**
     * 通过id查找账务账号
     *
     * @param accountId 账务id
     * @return 账务账号
     */
    Account findByAccountId(Integer accountId) throws AddServiceException;

    /**
     * 查找资费类型
     *
     * @return 所有资费类型
     */
    List<Cost> findCost();

    /**
     * 判断IP地址是否符合规范
     *
     * @param ip ip地址
     * @return 判断结果
     */
    String judgeIP(String ip) throws AddServiceException;

    /**
     * os账号判断
     *
     * @param osUsername os账号
     * @return 判断结果
     */
    String judgeOsUsername(String osUsername) throws AddServiceException;

    /**
     * 密码验证
     *
     * @param pwd 密码
     * @return 判断结果
     */
    String judgePwd(String pwd) throws AddServiceException;

    /**
     * 密码重复验证
     *
     * @param pwd  密码
     * @param pwd2 重复密码
     * @return 判断结果
     */
    String judgePwd2(String pwd, String pwd2) throws AddServiceException;

    /**
     * 通过serviceId查找
     *
     * @param serviceId id
     * @return servicezz
     */
    Servicezz findByServiceId(Integer serviceId);
}
