package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntity> {
    int searchTotalCount(int universityCode,
                         int schoolID,
                         int teacherID,
                         String courseTimeBegin,
                         String courseTimeEnd,
                         String dataStatus);

    List<CourseEntity> searchList(int universityCode,
                                  int schoolID,
                                  int teacherID,
                                  String courseTimeBegin,
                                  String courseTimeEnd,
                                  String dataStatus,
                                  int startIndex,
                                  int pageSize);

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
