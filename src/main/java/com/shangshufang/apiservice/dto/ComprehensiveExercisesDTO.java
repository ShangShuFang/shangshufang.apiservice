package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ComprehensiveExercisesDTO extends BaseDTO {
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String documentUrl;
    private String answerUrl;
    private String memo;
}
