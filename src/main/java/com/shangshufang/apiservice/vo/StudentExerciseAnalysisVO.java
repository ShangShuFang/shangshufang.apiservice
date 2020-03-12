package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentExerciseAnalysisVO {
    private String months;
    private int assignTotalCount;
    private int finishTotalCount;
    private int onceCompilationSuccessTotalCount;
    private int onceRunCorrectTotalCount;
}
