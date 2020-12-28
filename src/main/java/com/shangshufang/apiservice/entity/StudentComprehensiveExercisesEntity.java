package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesEntity extends BaseEntity {
    private int collectionID;
    private int studentID;
    private int exercisesID;
    private String exercisesTitle;
    private String examKnowledge;
    private int examType;
    private String examTypeText;
    private int difficultyLevel;
    private String difficultyLevelText;
    private int programLanguage;
    private String programLanguageName;
    private String gitUrl;
    private String memo;
}
