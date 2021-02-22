package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentAbilityAnalysisEntity extends BaseEntity {
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

    private int finishedKnowledgeCount;
    private float finishedKnowledgePercent;
    private int finishedUnitExercisesCount;
    private int joinedProjectCount;
    private double standardScore;
    private float positionSite;
    private String abilityLevel;
}