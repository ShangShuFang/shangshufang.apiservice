package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseExercisesEntity;
import com.shangshufang.apiservice.entity.ExercisesSingleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExercisesMapper extends BaseMapper<ExercisesSingleEntity> {
    int searchSingleTotalCount(int technologyID, int learningPhaseID);

    List<ExercisesSingleEntity> searchSingleList(int startIndex, int pageSize, int technologyID, int learningPhaseID);

    List<CourseExercisesEntity> searchCourseExercisesList(int universityCode, int schoolID, int courseID);

    ExercisesSingleEntity search(int exercisesID);

    int checkExercisesCodeExist(String exercisesCode);

    int delete(int exercisesID);
}
