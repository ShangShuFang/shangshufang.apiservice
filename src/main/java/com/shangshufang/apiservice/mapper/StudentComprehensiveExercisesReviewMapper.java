package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentComprehensiveExercisesReviewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentComprehensiveExercisesReviewMapper extends BaseMapper<StudentComprehensiveExercisesReviewEntity> {
    int searchTotalCount(@Param("studentID") int studentID,
                         @Param("exercisesID") int exercisesID);

    List<StudentComprehensiveExercisesReviewEntity> searchList(@Param("startIndex") int startIndex,
                                                         @Param("pageSize") int pageSize,
                                                         @Param("studentID") int studentID,
                                                         @Param("exercisesID") int exercisesID);
}
