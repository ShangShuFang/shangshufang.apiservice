package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesVO extends BaseVO {
    private int collectionID;
    private int studentID;
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailRectangle;
    private String documentUrl;
    private int reviewCompanyCount;
    private int reviewTotalCount;
    private String gitUrl;
    private String memo;
}
