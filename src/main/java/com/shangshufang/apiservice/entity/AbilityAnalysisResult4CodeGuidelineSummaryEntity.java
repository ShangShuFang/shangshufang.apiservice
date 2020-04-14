package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4CodeGuidelineSummaryEntity {
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int studentID;
    private int technologyID;
    private int codeStandardID;
    private String codeStandardName;
    private int codeStandardCount;
}
