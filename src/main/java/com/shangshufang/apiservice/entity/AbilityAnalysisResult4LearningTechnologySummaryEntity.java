package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4LearningTechnologySummaryEntity {
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int studentID;
    private int technologyID;
    private String technologyName;
    private float finishKnowledgePercent;
    private String abilityLevel;
    private float positionSite;
}
