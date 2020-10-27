package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class StudentCourseExercisesDTO extends BaseDTO {
    private int courseExercisesID;
    private int courseID;
    private int courseClass;
    private int studentID;
    private int technologyID;
    private int knowledgeID;
    private int exercisesID;
    private int exercisesType;
    private int exercisesSource;
    private String correctResult;
    private String corrector;
}
