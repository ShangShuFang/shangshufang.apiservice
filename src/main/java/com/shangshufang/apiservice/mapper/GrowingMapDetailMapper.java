package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.GrowingMapDetailEntity;
import com.shangshufang.apiservice.entity.LearningPathEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrowingMapDetailMapper extends BaseMapper<GrowingMapDetailEntity> {
    List<GrowingMapDetailEntity> searchList(@Param("growingID") int growingID);

    List<LearningPathEntity> searchList4Technology(@Param("growingID") int growingID);

    List<LearningPathEntity> searchList4LearningPhase(@Param("growingID") int growingID, @Param("technologyID") int technologyID);

    int delete(@Param("growingID") int growingID);
}
