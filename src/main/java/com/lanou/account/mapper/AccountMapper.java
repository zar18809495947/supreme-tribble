package com.lanou.account.mapper;

import com.lanou.account.bean.Account;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    /**
     * 修改状态
     *
     * @param record 修改的账户
     * @return 是否修改成功
     */
    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 查找所有账务账号
     *
     * @return 账务账号集合
     */
    List<Account> findAllAccount();

    /**
     * 模糊查询身份证号，名字，登录名，状态
     *
     * @param account 查询内容
     * @return 账户集合
     */
    List<Account> findByFuzzy(Account account);
}