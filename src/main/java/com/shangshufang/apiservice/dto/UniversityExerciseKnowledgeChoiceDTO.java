package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeChoiceDTO extends BaseDTO {
    private int exercisesID;
    private int universityCode;
    private int schoolID;
    private int teacherID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesType;
    private String choiceOptionsJson;
}
