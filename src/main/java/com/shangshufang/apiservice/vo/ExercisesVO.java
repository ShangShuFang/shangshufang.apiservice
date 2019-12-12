package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ExercisesVO extends BaseVO {
    private int exercisesID;
    private String  exercisesName;
    private String exercisesCode;
    private String exercisesType;
    private String exercisesTypeText;
    private int technologyCount;
    private int learningPhaseCount;
    private int knowledgeCount;
    private int documentCount;
}
