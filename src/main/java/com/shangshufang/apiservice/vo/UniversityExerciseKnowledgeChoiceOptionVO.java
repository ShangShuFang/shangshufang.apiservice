package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityExerciseKnowledgeChoiceOptionVO extends BaseVO {
    private int optionID;
    private int technologyID;
    private int knowledgeID;
    private int exercisesID;
    private String optionText;
    private Boolean rightAnswer;
}
