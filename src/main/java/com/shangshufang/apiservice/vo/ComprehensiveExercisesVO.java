package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ComprehensiveExercisesVO extends BaseVO {
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailRectangle;
    private String documentUrl;
    private String answerUrl;
    private String memo;
}
