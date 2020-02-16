package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseQuestionLeaveMessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseQuestionLeaveMessageMapper extends BaseMapper<CourseQuestionLeaveMessageEntity> {
    List<CourseQuestionLeaveMessageEntity> searchList(int questionID);
}
