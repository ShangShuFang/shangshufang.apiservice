package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeChoiceOptionEntity extends BaseEntity {
    private int optionID;
    private int technologyID;
    private int knowledgeID;
    private int exercisesID;
    private String optionText;
    private Boolean rightAnswer;
}
