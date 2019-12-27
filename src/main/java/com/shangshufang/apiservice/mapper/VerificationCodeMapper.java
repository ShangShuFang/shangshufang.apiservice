package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.VerificationCodeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerificationCodeMapper extends BaseMapper<VerificationCodeEntity> {
    VerificationCodeEntity search(String cellphone, String code);
}
