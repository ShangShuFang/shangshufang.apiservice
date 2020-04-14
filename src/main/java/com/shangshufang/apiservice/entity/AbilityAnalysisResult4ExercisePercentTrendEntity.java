package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4ExercisePercentTrendEntity {
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int studentID;
    private int technologyID;
    private String months;
    private float finishPercent;
    private float onceCompilationSuccessPercent;
    private float onceRunCorrectPercent;
}