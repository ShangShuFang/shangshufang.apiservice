package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesProgramAnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentCourseExercisesProgramAnswerMapper extends BaseMapper<StudentCourseExercisesProgramAnswerEntity>{
    StudentCourseExercisesProgramAnswerEntity searchLatestAnswer(@Param("courseExercisesID") int courseExercisesID,
                                                                 @Param("courseExercisesDetailID") int courseExercisesDetailID);
}
