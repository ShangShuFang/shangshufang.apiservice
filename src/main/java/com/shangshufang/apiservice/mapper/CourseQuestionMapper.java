package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseQuestionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseQuestionMapper extends BaseMapper<CourseQuestionEntity> {
    int searchTotalCount(int courseUniversityCode, int courseSchoolID, int courseID);

    List<CourseQuestionEntity> searchList(int startIndex, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID);
}
