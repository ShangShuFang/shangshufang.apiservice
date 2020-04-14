package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class AbilityAnalysisResult4ExerciseNumberTrendVO {
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
