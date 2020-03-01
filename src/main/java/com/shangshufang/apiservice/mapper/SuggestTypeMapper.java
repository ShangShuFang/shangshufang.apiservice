package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.SuggestTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuggestTypeMapper {
    List<SuggestTypeEntity> searchList(@Param("portal") String portal);
}
