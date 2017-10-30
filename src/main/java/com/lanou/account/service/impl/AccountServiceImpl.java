package com.lanou.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.mapper.AccountMapper;
import com.lanou.account.service.AccountService;
import com.lanou.account.service.exception.add.*;
import com.lanou.cost.bean.Cost;
import com.lanou.utils.IdCardUtils;
import com.lanou.utils.RegexUtils;
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

    @Override
    public void saveModiAccount(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public String judgeRealName(String realName) throws AddAccountException {
        if (realName == null || realName.trim().isEmpty()) {
            throw new RealNameIsEmptyException();
        }
        if (!RegexUtils.isChinaEnglishNum(realName)) {
            throw new RealNameFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeIdCardNo(String idCardNo) throws AddAccountException {
        if (idCardNo == null || idCardNo.trim().isEmpty()) {
            throw new IdCardNoIsEmptyException();
        }
        if (!IdCardUtils.isIdCardNo(idCardNo)) {
            System.out.println(1);
            throw new IdCardNoFormatErrorException();
        }
        List<Account> accountList = accountMapper.findByIdCardNo(idCardNo);
        if (accountList.size() != 0) {
            throw new IdCardNoRepeatException();
        }
        return null;
    }

    @Override
    public String judgeLoginName(String loginName) throws AddAccountException {
        if (loginName == null || loginName.trim().isEmpty()) {
            throw new LoginNameIsEmptyException();
        }
        if (!RegexUtils.isChinaEnglishNum2(loginName)) {
            throw new LoginNameFormatErrorException();
        }
        List<Account> accountList = accountMapper.findByLoginName(loginName);
        if (accountList.size() != 0) {
            throw new LoginNameRepeatException();
        }
        return null;
    }

    @Override
    public String judgePwd(String pwd) throws AddAccountException {
        if (pwd == null || pwd.trim().isEmpty()) {
            throw new PwdIsEmptyException();
        }
        if (!RegexUtils.isENG_NUM_(pwd)) {
            throw new PwdFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgePwd2(String pwd, String pwd2) throws AddAccountException {
        if (pwd2 == null || pwd2.trim().isEmpty()) {
            throw new Pwd2IsEmptyException();
        }
        if (!RegexUtils.isENG_NUM_(pwd2)) {
            throw new Pwd2FormatErrorException();
        }
        if (!pwd2.equals(pwd)) {
            throw new Pwd2NotIsPwdException();
        }
        return null;
    }

    @Override
    public String judgeTel(String tel) throws AddAccountException {
        if (tel == null || tel.trim().isEmpty()) {
            throw new TelIsEmptyException();
        }
        if (!RegexUtils.isPhone(tel)) {
            throw new TelFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeEmail(String email) throws AddAccountException {
        if (!RegexUtils.isEmail(email)) {
            throw new EmailFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeAddress(String address) throws AddAccountException {
        if (address.length() > 50) {
            throw new AddressFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeZip(String zip) throws AddAccountException {
        if (!RegexUtils.isCode(zip)) {
            throw new ZipFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeQQ(String qq) throws AddAccountException {
        if (!RegexUtils.isQQ(qq)) {
            throw new QQFormatErrorException();
        }
        return null;
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
