package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseSignUpEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseSignUpMapper extends BaseMapper<CourseSignUpEntity> {
    int searchCourseSignUpTotalCount(int universityCode, int schoolID, int courseID);

    List<CourseSignUpEntity> searchCourseSignUpList(int startIndex, int pageSize, int universityCode, int schoolID, int courseID);

    List<CourseSignUpEntity> searchAllStudent(@Param("startIndex") int startIndex,
                                              @Param("pageSize") int pageSize);

    List<CourseSignUpEntity> searchAllCourseSignUpList(@Param("universityCode") int universityCode,
                                                       @Param("schoolID") int schoolID,
                                                       @Param("courseID") int courseID);

    int searchStudentSignUpTotalCount(@Param("universityCode") int universityCode,
                                      @Param("schoolID") int schoolID,
                                      @Param("studentID") int studentID);

    List<CourseSignUpEntity> searchStudentSignUpList(@Param("startIndex") int startIndex,
                                                     @Param("pageSize") int pageSize,
                                                     @Param("universityCode") int universityCode,
                                                     @Param("schoolID") int schoolID,
                                                     @Param("studentID") int studentID);

    int searchTechnologyCourseSignUpTotalCount(int technologyID);

    List<CourseSignUpEntity> searchTechnologyCourseSignUpList(int startIndex, int pageSize, int technologyID);

    int searchIsSignUpCourse(@Param("studentID") int studentID,
                             @Param("universityCode") int universityCode,
                             @Param("schoolID") int schoolID,
                             @Param("courseID") int courseID);

    int searchIsAssistant(@Param("studentID") int studentID,
                          @Param("universityCode") int universityCode,
                          @Param("schoolID") int schoolID,
                          @Param("courseID") int courseID);

    int updateAssistant(CourseSignUpEntity entity);
}
