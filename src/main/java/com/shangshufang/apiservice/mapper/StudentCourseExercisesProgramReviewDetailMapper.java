package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesProgramReviewDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesProgramReviewDetailMapper extends BaseMapper<StudentCourseExercisesProgramReviewDetailEntity> {
    StudentCourseExercisesProgramReviewDetailEntity search(@Param("courseExercisesID") int courseExercisesID,
                                                           @Param("courseExercisesDetailID") int courseExercisesDetailID,
                                                           @Param("reviewID") int reviewID);

    StudentCourseExercisesProgramReviewDetailEntity searchLatest(@Param("courseExercisesID") int courseExercisesID,
                                                                 @Param("courseExercisesDetailID") int courseExercisesDetailID);
}
