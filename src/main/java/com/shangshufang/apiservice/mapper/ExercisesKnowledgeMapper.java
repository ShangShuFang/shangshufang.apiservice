package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExercisesKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExercisesKnowledgeMapper extends BaseMapper<ExercisesKnowledgeEntity>  {
    int searchTotalCount(@Param("exercisesID") int exercisesID);

    List<ExercisesKnowledgeEntity> searchList(int exercisesID);

    List<ExercisesKnowledgeEntity> search(int exercisesID);

    int delete(int exercisesID);
}
