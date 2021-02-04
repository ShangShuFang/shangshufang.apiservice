package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ComprehensiveExercisesVO extends BaseVO {
    private int exercisesID;
    private String exercisesTitle;
    private String exercisesDescription;
    private String examKnowledge;
    private int examType;
    private String examTypeText;
    private int difficultyLevel;
    private String difficultyLevelText;
    private String documentUrl;
}
