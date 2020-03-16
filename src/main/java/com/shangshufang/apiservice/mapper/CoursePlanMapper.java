package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CoursePlanEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoursePlanMapper extends BaseMapper<CoursePlanEntity> {
    List<CoursePlanEntity> searchList(int universityCode, int schoolID, int courseID);

    List<CoursePlanEntity> searchCourseClassList(int universityCode, int schoolID, int courseID);

    List<CoursePlanEntity> searchKnowledgeList4CourseClass(int universityCode, int schoolID, int courseID, int courseClass);

    int delete(int universityCode, int schoolID, int courseID);

    int delete4Class(int universityCode, int schoolID, int courseID);

    int updateDataStatus(int universityCode, int schoolID, int courseID, int courseClass);
}
