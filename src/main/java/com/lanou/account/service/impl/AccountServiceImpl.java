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
    public PageInfo<Account> findAccountPageInfo(Integer pageNum, Integer size) {
        return queryCostByPage(pageNum, size);
    }

    @Override
    public void stopAccount(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public List<Account> findByFuzzy(Account account) {
        return accountMapper.findByFuzzy(account);
    }

    private PageInfo<Account> queryCostByPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.findAllAccount();
//        System.out.println(costList);
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);
//        System.out.println(pageInfo);
        return pageInfo;
    }
}
