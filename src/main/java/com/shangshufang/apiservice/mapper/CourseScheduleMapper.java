package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CourseScheduleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseScheduleMapper extends BaseMapper<CourseScheduleEntity> {
    List<CourseScheduleEntity> searchList(int universityCode, int schoolID, int courseID);

    int delete(int universityCode, int schoolID, int courseID);
}
