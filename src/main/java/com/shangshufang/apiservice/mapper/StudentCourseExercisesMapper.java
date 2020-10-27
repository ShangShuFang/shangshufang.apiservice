package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesMapper extends BaseMapper<StudentCourseExercisesEntity> {
    List<StudentCourseExercisesEntity> searchListOfCourseClass(@Param("courseID") int courseID,
                                                               @Param("courseClassID") int courseClassID,
                                                               @Param("studentID") int studentID);
}
