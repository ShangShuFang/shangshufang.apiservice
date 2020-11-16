package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntity> {
    int searchTotalCountLikeName(@Param("content") String content);

    List<CourseEntity> searchListLikeName(int startIndex,
                                          int pageSize,
                                          String name);


    int searchTotalCount4Student(@Param("directionID") int directionID,
                                 @Param("categoryID") int categoryID,
                                 @Param("technologyID") int technologyID,
                                 @Param("universityCode") int universityCode,
                                 @Param("schoolID") int schoolID,
                                 @Param("isSelf") boolean isSelf,
                                 @Param("studentID") int studentID);

    List<CourseEntity> searchList4Student(@Param("startIndex") int startIndex,
                                          @Param("pageSize") int pageSize,
                                          @Param("directionID") int directionID,
                                          @Param("categoryID") int categoryID,
                                          @Param("technologyID") int technologyID,
                                          @Param("universityCode") int universityCode,
                                          @Param("schoolID") int schoolID,
                                          @Param("isSelf") boolean isSelf,
                                          @Param("studentID") int studentID);


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

    List<CourseEntity> searchSimpleList(@Param("universityCode") int universityCode,
                                        @Param("schoolID") int schoolID,
                                        @Param("teacherID") int teacherID,
                                        @Param("technologyID") int technologyID);

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
