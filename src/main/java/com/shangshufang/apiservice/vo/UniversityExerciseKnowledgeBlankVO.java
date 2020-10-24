package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeBlankVO extends BaseVO {
    private int exercisesID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int teacherID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String rightAnswer;
}
