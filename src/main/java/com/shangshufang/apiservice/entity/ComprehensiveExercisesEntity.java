package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ComprehensiveExercisesEntity extends BaseEntity {
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private String documentUrl;
    private String answerUrl;
}
