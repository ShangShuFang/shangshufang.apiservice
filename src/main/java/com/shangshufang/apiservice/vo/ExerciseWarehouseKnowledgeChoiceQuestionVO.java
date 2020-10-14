package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseWarehouseKnowledgeChoiceQuestionVO extends BaseVO {
    private int exercisesID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesSource;
    private String exercisesType;
    private List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> choiceOptions;
}
