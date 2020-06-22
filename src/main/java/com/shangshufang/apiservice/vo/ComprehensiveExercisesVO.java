package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ComprehensiveExercisesVO extends BaseVO {
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private String documentUrl;
    private String answerUrl;
}
