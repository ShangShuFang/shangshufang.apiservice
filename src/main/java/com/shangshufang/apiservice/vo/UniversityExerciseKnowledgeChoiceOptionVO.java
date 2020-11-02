package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeChoiceOptionVO extends BaseVO {
    private int optionID;
    private int exercisesID;
    private int technologyID;
    private int knowledgeID;
    private String optionText;
    private Boolean rightAnswer;
}
