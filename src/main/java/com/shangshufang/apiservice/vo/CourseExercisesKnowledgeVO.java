package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseExercisesKnowledgeVO{
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    private List<TechnologyKnowledgeExercisesVO> knowledgeExercisesList;
}
