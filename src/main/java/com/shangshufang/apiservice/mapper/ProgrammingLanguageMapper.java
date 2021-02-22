package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ProgrammingLanguageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProgrammingLanguageMapper {
    List<ProgrammingLanguageEntity> searchList();

    List<ProgrammingLanguageEntity> searchListByDirection(@Param("directionID") int directionID);
}
