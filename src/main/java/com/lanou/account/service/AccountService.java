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
    PageInfo<Account> findAccountPageInfo(Integer pageNum,Integer size);
}
