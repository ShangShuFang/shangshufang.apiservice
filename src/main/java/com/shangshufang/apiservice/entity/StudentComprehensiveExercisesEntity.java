package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesEntity extends BaseEntity {
    private int collectionID;
    private int studentID;
    private String fullName;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int majorID;
    private String majorName;
    private int exercisesID;
    private int examType;
    private String examTypeText;
    private String exercisesTitle;
    private String examKnowledge;
    private String exercisesContent;
    private int difficultyLevel;
    private String difficultyLevelText;
    private int programLanguage;
    private String programLanguageName;
    private String gitUrl;
    private String reviewMemo;
}
