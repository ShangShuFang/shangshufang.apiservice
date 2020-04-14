package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4ExerciseNumberTrendEntity {
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int studentID;
    private int technologyID;
    private String months;
    private int assignTotalCount;
    private int finishTotalCount;
    private int onceCompilationSuccessTotalCount;
    private int onceRunCorrectTotalCount;
}
