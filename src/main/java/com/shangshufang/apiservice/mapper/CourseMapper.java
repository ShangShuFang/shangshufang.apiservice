package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntity> {
    int searchTotalCount(int universityCode,
                         int schoolID,
                         int teacherID,
                         int technologyID,
                         String courseTimeBegin,
                         String dataStatus,
                         boolean isSelf);

    List<CourseEntity> searchList(int universityCode,
                                  int schoolID,
                                  int teacherID,
                                  int technologyID,
                                  String courseTimeBegin,
                                  String dataStatus,
                                  int startIndex,
                                  int pageSize,
                                  boolean isSelf);

    CourseEntity search(int universityCode,
                        int schoolID,
                        int teacherID,
                        int courseID);

    int checkCourseExist(int universityCode,
                             int schoolID,
                             String courseName,
                             String courseTimeBegin,
                             String courseTimeEnd);
}
