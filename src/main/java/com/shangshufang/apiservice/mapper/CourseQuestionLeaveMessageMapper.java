package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseQuestionLeaveMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseQuestionLeaveMessageMapper extends BaseMapper<CourseQuestionLeaveMessageEntity> {
    int searchTotalCount(@Param("questionID") int questionID);

    List<CourseQuestionLeaveMessageEntity> searchList(@Param("questionID") int questionID);

    int searchTotalCount4Student(@Param("studentID") int studentID);

    List<CourseQuestionLeaveMessageEntity> searchList4Student(@Param("startIndex") int startIndex,
                                                              @Param("pageSize") int pageSize,
                                                              @Param("studentID") int studentID);
}
