package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.LearningPhaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningPhaseMapper {
    List<LearningPhaseEntity> searchList(@Param("technologyID") int technologyID);
}
