package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesCodeReviewDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesCodeReviewDetailMapper extends BaseMapper<StudentCourseExercisesCodeReviewDetailEntity> {
    List<StudentCourseExercisesCodeReviewDetailEntity> searchList(@Param("courseExercisesID") int courseExercisesID,
                                                                  @Param("courseExercisesDetailID") int courseExercisesDetailID,
                                                                  @Param("reviewID") int reviewID);
}
