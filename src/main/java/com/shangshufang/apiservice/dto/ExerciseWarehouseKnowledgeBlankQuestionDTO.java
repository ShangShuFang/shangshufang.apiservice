package com.shangshufang.apiservice.dto;
import lombok.Data;

@Data
public class ExerciseWarehouseKnowledgeBlankQuestionDTO extends BaseDTO {
    private int exercisesID;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesSource;
    private String rightAnswer;
}
