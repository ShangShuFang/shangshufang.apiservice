package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesReviewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesReviewMapper extends BaseMapper<StudentCourseExercisesReviewEntity> {
    List<StudentCourseExercisesReviewEntity> searchList(@Param("courseExercisesID") int courseExercisesID,
                                                        @Param("courseExercisesDetailID") int courseExercisesDetailID);
}
