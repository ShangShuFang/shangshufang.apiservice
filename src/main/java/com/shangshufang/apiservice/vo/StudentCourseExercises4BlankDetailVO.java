package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentCourseExercises4BlankDetailVO extends BaseVO {
    private int courseExercisesDetailID;
    private int courseExercisesID;
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;
    private int exercisesID;
    private String exercisesTitle;
    private int exercisesType;
    private int exercisesSourceType;
    private String exercisesSource;
    private String correctResult;
    private String corrector;

    private String rightAnswer;

    private String fillInContent;
}
