package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4StudentMainInfoEntity extends BaseEntity {
    private int analysisID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int majorID;
    private String majorName;
    private int studentID;
    private String studentName;
    private String studentPhoto;
    private String studentCellphone;
    private int technologyID;
    private String technologyName;
    private int knowledgeTotalCount;
    private int finishKnowledgeCount;
    private float finishKnowledgePercent;
    private int finishExercisesCount;
    private int finishUnionExercisesCount;
    private int finishProjectCount;
    private float onceCompilationSuccessRate;
    private float onceRunSuccessRate;
    private double standardScore;
    private float positionSite;
    private String abilityLevel;
}