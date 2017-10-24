package com.lanou.account.service;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;

import java.util.List;

/**
 * @author zar on 2017/10/23.
 */
public interface AccountService {

    /**
     * 查找所有账务账号
     *
     * @return 账务账号集合
     */
    List<Account> findAllAccount();

    /**
     * 暂停账户
     *
     * @param account 账户id
     */
    void stopAccount(Account account);

    /**
     * 模糊查询 名字，登录名，身份证，状态,并进行分页
     *
     * @param account 查询内容
     * @param pageNum 第几页
     * @param pageSize 一页数据多少
     * @return 分页信息
     */
    PageInfo<Account> findByFuzzy(Account account, Integer pageNum, Integer pageSize);
}
