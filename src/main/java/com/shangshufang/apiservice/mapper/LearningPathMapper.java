package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.LearningPathEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LearningPathMapper extends BaseMapper<LearningPathEntity> {
    int searchTotalCount(int technologyID, int learningPhase);

    List<LearningPathEntity> searchList(int startIndex, int pageSize, int technologyID, int learningPhase);

    List<LearningPathEntity> searchUsingTechnology();

    List<LearningPathEntity> searchUsingLearningPhase(int technologyID);

    List<LearningPathEntity> searchUsingKnowledge(int technologyID, int learningPhase);

    int delete(int technologyID, int learningPhase);
}
