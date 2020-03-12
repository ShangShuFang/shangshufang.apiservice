package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentExercisePercentAnalysisVO {
    private String months;
    private int finishPercent;
    private int onceCompilationSuccessPercent;
    private int onceRunCorrectPercent;
}
