package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesMapper extends BaseMapper<StudentCourseExercisesEntity> {
    int searchTotalCount(@Param("courseID") int courseID,
                         @Param("dataStatus") String dataStatus,
                         @Param("studentName") String studentName);

    StudentCourseExercisesEntity search(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                  @Param("pageSize") int pageSize,
                                                  @Param("courseID") int courseID,
                                                  @Param("dataStatus") String dataStatus,
                                                  @Param("studentName") String studentName);
}
