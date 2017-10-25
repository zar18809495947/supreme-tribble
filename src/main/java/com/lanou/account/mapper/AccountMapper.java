package com.lanou.account.mapper;

import com.lanou.account.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    /**
     * 添加账务信息
     *
     * @param record 账务资料
     * @return 是否添加成功, 自动赋值id
     */
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

    /**
     * 通过身份证号查询
     *
     * @param idCardNo 身份证号
     * @return 返回集合
     */
    List<Account> findByIdCardNo(String idCardNo);

    /**
     * 通过登录名查询
     *
     * @param loginName 登录名
     * @return 返回集合
     */
    List<Account> findByLoginName(String loginName);

    /**
     * 通过id查找账务账号，并且未删除
     *
     * @param accountId 账务账号id
     * @return 账务账号
     */
    Account findByAccountId(@Param("accountId") Integer accountId);
}