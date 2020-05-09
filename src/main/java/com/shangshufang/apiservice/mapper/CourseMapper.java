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
                         int directionID,
                         int categoryID,
                         boolean isSelf);

    List<CourseEntity> searchList(int universityCode,
                                  int schoolID,
                                  int teacherID,
                                  int technologyID,
                                  String courseTimeBegin,
                                  String dataStatus,
                                  int startIndex,
                                  int pageSize,
                                  int directionID,
                                  int categoryID,
                                  boolean isSelf);

    int searchStartedTotalCount(int universityCode,
                                int schoolID,
                                int teacherID,
                                int technologyID,
                                String courseTimeBegin,
                                String dataStatus,
                                int directionID,
                                int categoryID,
                                boolean isSelf);

    List<CourseEntity> searchStartedList(int universityCode,
                                         int schoolID,
                                         int teacherID,
                                         int technologyID,
                                         String courseTimeBegin,
                                         String dataStatus,
                                         int startIndex,
                                         int pageSize,
                                         int directionID,
                                         int categoryID,
                                         boolean isSelf);

    int searchSimpleTotalCount(int universityCode, int schoolID, int teacherID, int technologyID);

    List<CourseEntity> searchSimpleList(int universityCode, int schoolID, int teacherID, int technologyID);

    CourseEntity search(int universityCode,
                        int schoolID,
                        int courseID,
                        String dataStatus);

    int checkCourseExist(int universityCode,
                         int schoolID,
                         String courseName,
                         String courseTimeBegin,
                         String courseTimeEnd);

    int updateCourseToStart();
}
