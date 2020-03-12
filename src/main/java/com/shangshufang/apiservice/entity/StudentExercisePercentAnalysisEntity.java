package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentExercisePercentAnalysisEntity {
    private String months;
    private int finishPercent;
    private int onceCompilationSuccessPercent;
    private int onceRunCorrectPercent;
}
