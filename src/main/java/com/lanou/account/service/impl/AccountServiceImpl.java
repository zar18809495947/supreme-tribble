package com.lanou.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.mapper.AccountMapper;
import com.lanou.account.service.AccountService;
import com.lanou.cost.bean.Cost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zar on 2017/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllAccount();
    }

    @Override
    public void stopAccount(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public PageInfo<Account> findByFuzzy(Account account, Integer pageNum, Integer pageSize) {
        PageInfo<Account> pageInfo = queryAccountByPageFuzzy(account, pageNum, pageSize);
        return pageInfo;
    }

    @Override
    public void addAccount(Account account) {
        accountMapper.insertSelective(account);
    }

    @Override
    public Account findByAccountId(Integer accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    private PageInfo<Account> queryCostByPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.findAllAccount();
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);
        return pageInfo;
    }

    private PageInfo<Account> queryAccountByPageFuzzy(Account account, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Account> byFuzzy = accountMapper.findByFuzzy(account);
        PageInfo<Account> pageInfo = new PageInfo<>(byFuzzy);
        return pageInfo;
    }
}
