package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity extends BaseEntity {
    private int optionID;
    private int exercisesID;
    private String optionText;
    private Boolean rightAnswer;
}
