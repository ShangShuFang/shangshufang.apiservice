package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeBlankEntity extends BaseEntity {
    private int exercisesID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int teacherID;
    private String teacherName;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String rightAnswer;
}
