package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentExercisePercentAnalysisVO {
    private String months;
    private float finishPercent;
    private float onceCompilationSuccessPercent;
    private float onceRunCorrectPercent;
}
