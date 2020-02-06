package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseExercisesEntity extends BaseEntity {
    private int courseClass;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    private int exercisesID;
    private String  exercisesName;
    private String exercisesCode;
    private String exercisesType;
}
