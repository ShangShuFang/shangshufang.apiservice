package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesEntity extends BaseEntity {
    private int collectionID;
    private int studentID;
    private int exercisesID;
    private String exercisesName;
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailRectangle;
    private String documentUrl;
    private String gitUrl;
    private String memo;
    private int reviewCompanyCount;
    private int reviewTotalCount;
}
