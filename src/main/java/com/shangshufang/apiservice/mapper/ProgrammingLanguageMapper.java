package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ProgrammingLanguageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProgrammingLanguageMapper {
    List<ProgrammingLanguageEntity> searchList();
}
