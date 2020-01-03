package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.LearningPathEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LearningPathMapper {
    List<LearningPathEntity> searchTechnology();

    List<LearningPathEntity> searchLearningPhase(int technologyID);

    List<LearningPathEntity> searchKnowledge(int technologyID, int learningPhase);
}
