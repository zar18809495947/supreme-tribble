package com.lanou.service.mapper;

import com.lanou.account.bean.Account;
import com.lanou.service.bean.Servicezz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Servicezz record);

    /**
     * 添加Service
     *
     * @param record 添加的内容
     * @return 是否添加成功
     */
    int insertSelective(Servicezz record);

    Servicezz selectByPrimaryKey(Integer serviceId);

    /**
     * 根据条件更新Service
     *
     * @param record 更新内容
     * @return 是否成功
     */
    int updateByPrimaryKeySelective(Servicezz record);

    int updateByPrimaryKey(Servicezz record);

    List<Servicezz> findAll();

    /**
     * 模糊查询 os用户名，ip，身份证号
     *
     * @param osUsername os用户名
     * @param unixHost   ip地址
     * @param idcardNo   身份证号
     * @return 查询集合
     */
    List<Servicezz> selectServiceFuzzy(@Param("osUsername") String osUsername,
                                       @Param("unixHost") String unixHost,
                                       @Param("idcardNo") String idcardNo,
                                       @Param("status") String status);

    /**
     * 通过os用户名查找
     *
     * @param osUsername os用户名
     * @return 查找结果
     */
    Servicezz findByOSUsername(String osUsername);

    /**
     * 通过costId查找业务账号
     * @param costId
     * @return
     */
    List<Servicezz> findByCostId(Integer costId);
}