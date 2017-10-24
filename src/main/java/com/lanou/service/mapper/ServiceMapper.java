package com.lanou.service.mapper;

import com.lanou.service.bean.Service;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    List<Service> findAll();

    /**
     * 模糊查询 os用户名，ip，身份证号
     *
     * @param osUsername os用户名
     * @param unixHost   ip地址
     * @param idcardNo   身份证号
     * @return 查询集合
     */
    List<Service> selectServiceFuzzy(@Param("osUsername") String osUsername,
                                     @Param("unixHost") String unixHost,
                                     @Param("idcardNo") String idcardNo,
                                     @Param("status") String status);
}