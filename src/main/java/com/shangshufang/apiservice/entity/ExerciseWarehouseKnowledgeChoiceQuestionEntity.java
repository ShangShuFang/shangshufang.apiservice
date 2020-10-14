package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExerciseWarehouseKnowledgeChoiceQuestionEntity extends BaseEntity {
    private int exercisesID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesSource;
    private String exercisesType;
}
