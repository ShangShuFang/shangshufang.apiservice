package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentExercisePercentAnalysisEntity {
    private String months;
    private float finishPercent;
    private float onceCompilationSuccessPercent;
    private float onceRunCorrectPercent;
}
