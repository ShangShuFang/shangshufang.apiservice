package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesDetailEntity extends BaseEntity {
    private int courseExercisesDetailID;
    private int courseExercisesID;
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;
    private int exercisesID;
    private int exercisesType;
    private int exercisesSource;
    private String programExercisesCodeUri;
    private String correctResult;
    private String corrector;
}
