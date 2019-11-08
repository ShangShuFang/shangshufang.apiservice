package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.SystemAdminEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemAdminMapper {
    SystemAdminEntity login(String cellphone, String password);
}
