package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExercisesImageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExercisesImageMapper extends BaseMapper<ExercisesImageEntity> {
    List<ExercisesImageEntity> searchList(int exercisesID);

    int delete(int exercisesID);
}
