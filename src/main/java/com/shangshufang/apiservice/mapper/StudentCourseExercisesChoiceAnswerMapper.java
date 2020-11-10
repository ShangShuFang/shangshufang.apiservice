package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesChoiceAnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentCourseExercisesChoiceAnswerMapper extends BaseMapper<StudentCourseExercisesChoiceAnswerEntity> {
    StudentCourseExercisesChoiceAnswerEntity searchLatestAnswer(@Param("courseExercisesID") int courseExercisesID,
                                                                      @Param("courseExercisesDetailID") int courseExercisesDetailID);
}
