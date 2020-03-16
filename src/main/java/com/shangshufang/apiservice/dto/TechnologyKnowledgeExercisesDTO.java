package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyKnowledgeExercisesDTO extends BaseDTO {
    private int exercisesID;
    private int technologyID;
    private int learningPhaseID;
    private int knowledgeID;
    private String exercisesJson;
}
