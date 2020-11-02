package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesDetailMapper extends BaseMapper<StudentCourseExercisesDetailEntity> {
    List<StudentCourseExercisesDetailEntity> searchChoiceList(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesDetailEntity> searchBlankList(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesDetailEntity> searchProgramList(@Param("courseExercisesID") int courseExercisesID);
}
