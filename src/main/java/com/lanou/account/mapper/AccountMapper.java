package com.lanou.account.mapper;

import com.lanou.account.bean.Account;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 查找所有账务账号
     *
     * @return 账务账号集合
     */
    List<Account> findAllAccount();
}