package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExercisesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExercisesMapper extends BaseMapper<ExercisesEntity> {
    int searchTotalCount(String exercisesType, int technologyID, int learningPhaseID);

    List<ExercisesEntity> searchList(int startIndex, int pageSizeString,  String exercisesType, int technologyID, int learningPhaseID);

    ExercisesEntity search(int exercisesID);

    int checkExercisesCodeExist(String exercisesCode);

    int delete(int exercisesID);
}
