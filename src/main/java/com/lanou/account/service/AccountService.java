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
     * 查找页面信息
     *
     * @return 本页信息
     */
    PageInfo<Account> findAccountPageInfo(Integer pageNum, Integer size);

    /**
     * 暂停账户
     *
     * @param account 账户id
     */
    void stopAccount(Account account);

    /**
     * 模糊查询 名字，登录名，身份证，状态
     *
     * @param account 查询内容
     * @return 账户集合
     */
    List<Account> findByFuzzy(Account account);
}
