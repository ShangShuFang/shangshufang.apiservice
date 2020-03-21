package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityStudentAbilityAnalysisVO {
    private int analysisID;
    private int studentUniversityCode;
    private String studentUniversityName;
    private int studentSchoolID;
    private String studentSchoolName;
    private int studentID;
    private String studentName;
    private String studentPhoto;
    private String studentCellphone;
    private int technologyID;
    private int knowledgeTotalCount;
    private int finishKnowledgeCount;
    private float finishKnowledgePercent;
    private int finishExercisesCount;
    private float onceCompilationSuccessRate;
    private float onceRunSuccessRate;
    private float positionSite;
    private String abilityLevel;
}
