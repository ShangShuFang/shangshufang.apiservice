package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class AbilityAnalysisResult4StudentMainInfoVO {
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
    private float onceCompilationSuccessRate;
    private float onceRunSuccessRate;
    private float positionSite;
    private String abilityLevel;
}
