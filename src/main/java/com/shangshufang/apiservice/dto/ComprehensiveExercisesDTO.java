package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ComprehensiveExercisesDTO extends BaseDTO {
    private int exercisesID;
    private String exercisesTitle;
    private String exercisesDescription;
    private String examKnowledge;
    private int examType;
    private int difficultyLevel;
}
