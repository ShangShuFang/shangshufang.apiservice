package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class StudentCourseExercises4ProgramDetailVO extends BaseVO {
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
    private String submitSourceCodeUrl;
    private String answerSourceCodeUrl;
    private String compilationResult;
    private String runResult;
    private String codeStandardResult;
    private String reviewResult;
    private String reviewMemo;
    private List<CodeStandardVO> codeStandardErrorList;

}
