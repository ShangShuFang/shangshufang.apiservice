package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ExercisesKnowledgeDTO extends BaseDTO {
    private int exercisesKnowledgeID;
    private int exercisesID;
    private int technologyID;
    private int learningPhaseID;
    private int knowledgeID;
}
