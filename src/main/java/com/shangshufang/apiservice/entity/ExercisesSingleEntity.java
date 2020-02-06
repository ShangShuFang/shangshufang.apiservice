package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExercisesSingleEntity extends BaseEntity {
    private int exercisesID;
    private String  exercisesName;
    private String exercisesCode;
    private String exercisesType;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
}
