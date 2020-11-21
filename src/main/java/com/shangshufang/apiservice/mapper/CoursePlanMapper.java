package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CoursePlanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoursePlanMapper extends BaseMapper<CoursePlanEntity> {
    int searchTotalCount(@Param("universityCode") int universityCode,
                         @Param("schoolID") int schoolID,
                         @Param("courseID") int courseID);

    List<CoursePlanEntity> searchList(@Param("startIndex") int startIndex,
                                      @Param("pageSize") int pageSize,
                                      @Param("universityCode") int universityCode,
                                      @Param("schoolID") int schoolID,
                                      @Param("courseID") int courseID);

    List<CoursePlanEntity> searchAll(int universityCode, int schoolID, int courseID);

    List<CoursePlanEntity> searchCourseClassList(int universityCode, int schoolID, int courseID);

    List<CoursePlanEntity> searchKnowledgeList4CourseClass(int universityCode, int schoolID, int courseID, int courseClass);

    int delete(int universityCode, int schoolID, int courseID);

    int delete4Class(int universityCode, int schoolID, int courseID);

    int updateDataStatus(int universityCode, int schoolID, int courseID, int courseClass);
}
