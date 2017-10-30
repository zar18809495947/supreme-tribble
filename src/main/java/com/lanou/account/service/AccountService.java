package com.lanou.account.service;

import com.github.pagehelper.PageInfo;
import com.lanou.account.bean.Account;
import com.lanou.account.service.exception.add.AddAccountException;

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
     * @param account  查询内容
     * @param pageNum  第几页
     * @param pageSize 一页数据多少
     * @return 分页信息
     */
    PageInfo<Account> findByFuzzy(Account account, Integer pageNum, Integer pageSize);

    /**
     * 添加账户信息
     *
     * @param account 账务信息
     */
    void addAccount(Account account);

    /**
     * 通过id查找账务信息
     *
     * @param accountId 账务id
     * @return 账务信息
     */
    Account findByAccountId(Integer accountId);

    /**
     * 保存修改的账务信息
     *
     * @param account 账务信息
     */
    void saveModiAccount(Account account);

    /**
     * 实名验证
     *
     * @param realName 实名
     * @return 判断结果
     */
    String judgeRealName(String realName) throws AddAccountException;

    /**
     * 身份证验证
     *
     * @param idCardNo 身份证号
     * @return 判断结果
     */
    String judgeIdCardNo(String idCardNo) throws AddAccountException;

    /**
     * 登录名验证
     *
     * @param loginName 登录名
     * @return 判断结果
     */
    String judgeLoginName(String loginName) throws AddAccountException;

    /**
     * 密码验证
     *
     * @param pwd 密码
     * @return 判断结果
     */
    String judgePwd(String pwd) throws AddAccountException;

    /**
     * 密码重复验证
     *
     * @param pwd  密码
     * @param pwd2 重复密码
     * @return 判断结果
     */
    String judgePwd2(String pwd, String pwd2) throws AddAccountException;

    /**
     * 电话验证
     *
     * @param tel 电话
     * @return 判断结果
     */
    String judgeTel(String tel) throws AddAccountException;

    /**
     * 邮件验证
     *
     * @param email 邮件
     * @return 判断结果
     */
    String judgeEmail(String email) throws AddAccountException;

    /**
     * 地址验证
     *
     * @param address 地址
     * @return 判断结果
     */
    String judgeAddress(String address) throws AddAccountException;

    /**
     * 邮编验证
     *
     * @param zip 邮编
     * @return 判断结果
     */
    String judgeZip(String zip) throws AddAccountException;

    /**
     * QQ验证
     *
     * @param qq qq号
     * @return 判断结果
     */
    String judgeQQ(String qq) throws AddAccountException;
}
