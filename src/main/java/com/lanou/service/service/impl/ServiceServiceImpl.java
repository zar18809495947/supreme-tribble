package com.lanou.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.mapper.AccountMapper;
import com.lanou.cost.bean.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.service.bean.Servicezz;
import com.lanou.service.mapper.ServiceMapper;
import com.lanou.service.service.ServiceService;
import com.lanou.service.service.exception.add.*;
import com.lanou.service.service.exception.start.AccountStatusIsNotStartException;
import com.lanou.service.service.exception.start.StartServiceException;
import com.lanou.utils.RegexUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private CostMapper costMapper;

    @Override
    public List<Servicezz> findAll() {
        return serviceMapper.findAll();
    }

    @Override
    public PageInfo<Servicezz> findServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        PageInfo<Servicezz> pageInfo = fuzzyFindServicePageInfo(osUsername, unixHost, idcardNo, status, no, size);
        return pageInfo;
    }

    @Override
    public void updateService(Servicezz servicezz) throws StartServiceException {
        if ("1".equals(servicezz.getStatus())) {
            Servicezz service1 = serviceMapper.selectByPrimaryKey(servicezz.getServiceId());
            if (!"1".equals(service1.getAccount().getStatus())) {
                throw new AccountStatusIsNotStartException();
            }
            servicezz.setPauseDate("0000-00-00");
        } else if ("2".equals(servicezz.getStatus())) {
            servicezz.setPauseDate(new Timestamp(System.currentTimeMillis()).toString());
        } else {
            servicezz.setCloseDate(new Timestamp(System.currentTimeMillis()).toString());
        }
        serviceMapper.updateByPrimaryKeySelective(servicezz);
    }

    @Override
    public void addService(Servicezz servicezz) {
        serviceMapper.insertSelective(servicezz);
    }

    @Override
    public List<Account> findIdCard(String idcard) throws AddServiceException {
        List<Account> byIdCardNo = accountMapper.findByIdCardNo(idcard);
        if (byIdCardNo.size() == 0) {
            throw new IdcardNotExistException();
        }
        return byIdCardNo;
    }

    @Override
    public Account findByAccountId(Integer accountId) throws AddServiceException {
        Account byAccountId = accountMapper.findByAccountId(accountId);
        if (byAccountId == null) {
            throw new AccountNotExistException();
        }
        return byAccountId;
    }

    @Override
    public List<Cost> findCost() {
        return costMapper.findAllCost();
    }

    @Override
    public String judgeIP(String ip) throws AddServiceException {
        if (ip == null || ip.trim().isEmpty()) {
            throw new IPAddressIsEmptyException();
        }
        if (!RegexUtils.isIpaddress(ip)) {
            throw new IPAddressFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgeOsUsername(String osUsername) throws AddServiceException {
        if (osUsername == null || osUsername.trim().isEmpty()) {
            throw new OsUsernameIsEmptyException();
        }
        if (!RegexUtils.Regular(osUsername, "^\\w{1,8}")) {
            throw new OSUsernameFormatErrorException();
        }
        Servicezz byOSUsername = serviceMapper.findByOSUsername(osUsername);
        if (byOSUsername != null) {
            throw new OSUsernamRepeatException();
        }
        return null;
    }

    @Override
    public String judgePwd(String pwd) throws AddServiceException {
        if (pwd == null || pwd.trim().isEmpty()) {
            throw new PwdIsEmptyException();
        }
        if (!RegexUtils.isENG_NUM_(pwd)) {
            throw new PwdFormatErrorException();
        }
        return null;
    }

    @Override
    public String judgePwd2(String pwd, String pwd2) throws AddServiceException {
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

    private PageInfo<Servicezz> fuzzyFindServicePageInfo(String osUsername, String unixHost, String idcardNo, String status, Integer no, Integer size) {
        no = no == null ? 1 : no;
        size = size == null ? 5 : size;
        PageHelper.startPage(no, size);
        List<Servicezz> services = serviceMapper.selectServiceFuzzy(osUsername, unixHost, idcardNo, status);
        return new PageInfo<>(services);
    }
}
