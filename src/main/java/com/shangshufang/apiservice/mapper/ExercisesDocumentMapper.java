package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExercisesDocumentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExercisesDocumentMapper extends BaseMapper<ExercisesDocumentEntity> {
    List<ExercisesDocumentEntity> searchList(int exercisesID);

    int delete(int exercisesID);
}
