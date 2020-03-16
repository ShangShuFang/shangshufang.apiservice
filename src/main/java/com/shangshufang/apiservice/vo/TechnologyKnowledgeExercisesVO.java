package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyKnowledgeExercisesVO extends BaseVO {
    private int exercisesID;
    private int technologyID;
    private int learningPhaseID;
    private int knowledgeID;
    private String knowledgeName;
    private String documentUrl;
    private String answerUrl;
}
