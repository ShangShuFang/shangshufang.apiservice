package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyKnowledgeExercisesEntity extends BaseEntity {
    private int exercisesID;
    private int technologyID;
    private int learningPhaseID;
    private int knowledgeID;
    private String knowledgeName;
    private String documentUrl;
    private String answerUrl;
}
