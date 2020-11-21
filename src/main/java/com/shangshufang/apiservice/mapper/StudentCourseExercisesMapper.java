package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesMapper extends BaseMapper<StudentCourseExercisesEntity> {
    int searchTotalCountNew(@Param("technologyID") int technologyID,
                            @Param("universityCode") int universityCode,
                            @Param("schoolID") int schoolID,
                            @Param("courseID") int courseID,
                            @Param("studentID") int studentID,
                            @Param("studentName") String studentName,
                            @Param("dataStatus") String dataStatus);

    int searchTotalCount(@Param("courseID") int courseID,
                         @Param("dataStatus") String dataStatus,
                         @Param("studentName") String studentName);

    int searchTotalCount4Student(@Param("courseID") int courseID,
                                 @Param("studentID") int studentID,
                                 @Param("dataStatus") String dataStatus);

    StudentCourseExercisesEntity search(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesEntity> searchListNew(@Param("startIndex") int startIndex,
                                                     @Param("pageSize") int pageSize,
                                                     @Param("technologyID") int technologyID,
                                                     @Param("universityCode") int universityCode,
                                                     @Param("schoolID") int schoolID,
                                                     @Param("courseID") int courseID,
                                                     @Param("studentID") int studentID,
                                                     @Param("studentName") String studentName,
                                                     @Param("dataStatus") String dataStatus);

    List<StudentCourseExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                  @Param("pageSize") int pageSize,
                                                  @Param("courseID") int courseID,
                                                  @Param("dataStatus") String dataStatus,
                                                  @Param("studentName") String studentName);

    List<StudentCourseExercisesEntity> searchList4Student(@Param("startIndex") int startIndex,
                                                          @Param("pageSize") int pageSize,
                                                          @Param("courseID") int courseID,
                                                          @Param("studentID") int studentID,
                                                          @Param("dataStatus") String dataStatus);

    List<StudentCourseExercisesEntity> searchList4CourseClass(@Param("courseID") int courseID,
                                                              @Param("courseClass") int courseClass,
                                                              @Param("dataStatus") String dataStatus);
}
