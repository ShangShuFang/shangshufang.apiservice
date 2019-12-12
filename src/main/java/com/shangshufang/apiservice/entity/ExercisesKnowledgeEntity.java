package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExercisesKnowledgeEntity extends BaseEntity {
    private int exercisesKnowledgeID;
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
}
