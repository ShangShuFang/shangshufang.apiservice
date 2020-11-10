package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesBlankAnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesBlankAnswerMapper extends BaseMapper<StudentCourseExercisesBlankAnswerEntity> {
    StudentCourseExercisesBlankAnswerEntity searchLatestAnswer(@Param("courseExercisesID") int courseExercisesID,
                                                                      @Param("courseExercisesDetailID") int courseExercisesDetailID);
}
