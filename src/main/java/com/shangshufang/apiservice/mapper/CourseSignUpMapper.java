package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseSignUpEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseSignUpMapper extends BaseMapper<CourseSignUpEntity> {
    int searchCourseSignUpTotalCount(int universityCode, int schoolID, int courseID);

    List<CourseSignUpEntity> searchCourseSignUpList(int startIndex, int pageSize, int universityCode, int schoolID, int courseID);

    int searchStudentSignUpTotalCount(int universityCode, int schoolID, int studentID);

    List<CourseSignUpEntity> searchStudentSignUpList(int startIndex, int pageSize, int universityCode, int schoolID, int studentID);

    int searchTechnologyCourseSignUpTotalCount(int technologyID);

    List<CourseSignUpEntity> searchTechnologyCourseSignUpList(int startIndex, int pageSize, int technologyID);
}
