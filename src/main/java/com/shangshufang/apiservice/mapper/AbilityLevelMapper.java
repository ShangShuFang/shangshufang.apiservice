package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.AbilityLevelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AbilityLevelMapper {
    List<AbilityLevelEntity> searchList();

    AbilityLevelEntity search(@Param("levelCode") String levelCode);
}
