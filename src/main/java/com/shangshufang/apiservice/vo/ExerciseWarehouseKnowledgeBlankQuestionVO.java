package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ExerciseWarehouseKnowledgeBlankQuestionVO extends BaseVO {
    private int exercisesID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesSource;
    private String rightAnswer;
}
