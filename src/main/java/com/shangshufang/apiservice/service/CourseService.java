package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.ibatis.annotations.Param;

public interface CourseService extends BaseService<CourseDTO> {
    UnifiedResponse findListLikeName (int pageNumber,
                                      int pageSize,
                                      String content);

    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int universityCode,
                             int schoolID,
                             int teacherID,
                             int directionID,
                             int categoryID,
                             int technologyID,
                             String courseTimeBegin,
                             String dataStatus,
                             boolean isSelf,
                             String searchType);

    UnifiedResponse findList4Student(int pageNumber,
                                     int pageSize,
                                     int directionID,
                                     int categoryID,
                                     int technologyID,
                                     int universityCode,
                                     int schoolID,
                                     boolean isSelf,
                                     int studentID);

    UnifiedResponse findSimpleList(int universityCode, int schoolID, int teacherID, int technologyID);

    UnifiedResponse find(int universityCode,
                         int schoolID,
                         int courseID,
                         String dataStatus);

    UnifiedResponse findKnowledgeLearnAnalyse(int pageNumber,
                                              int pageSize,
                                              int universityCode,
                                              int schoolID,
                                              int courseID);

    UnifiedResponse checkCourseExist(int universityCode,
                                      int schoolID,
                                      String courseName,
                                      String courseTimeBegin,
                                      String courseTimeEnd);

    UnifiedResponse changeCourseBaseInfo(CourseDTO dto);

    UnifiedResponse changeCourseSchedule(CourseDTO dto);

    UnifiedResponse changeCoursePlan(CourseDTO dto);

    UnifiedResponse startCourse();
}
