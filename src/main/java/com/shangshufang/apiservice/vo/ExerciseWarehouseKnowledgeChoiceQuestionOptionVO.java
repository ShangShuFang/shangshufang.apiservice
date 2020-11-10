package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ExerciseWarehouseKnowledgeChoiceQuestionOptionVO extends BaseVO {
    private int optionID;
    private int exercisesID;
    private String optionText;
    private Boolean rightAnswer;
    private Boolean selectedAnswer;
}
