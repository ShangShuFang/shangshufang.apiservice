package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentAbilityAnalysisVO {
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
    private int languageID;
    private String technologyName;
    private String technologyThumbnailSquare;
    private String technologyThumbnailRectangle;
    private int knowledgeTotalCount;
    private int finishedKnowledgeCount;
    private float finishedKnowledgePercent;
    private int weaknessKnowledgeCount;
    private int noLearnKnowledgeCount;

    private int finishedUnitExercisesCount;
    private int joinedProjectCount;
    private double standardScore;
    private float positionSite;
    private String abilityLevel;
}
