package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentExerciseAnalysisEntity {
    private String months;
    private int assignTotalCount;
    private int finishTotalCount;
    private int onceCompilationSuccessTotalCount;
    private int onceRunCorrectTotalCount;
}
