package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesReviewEntity extends BaseEntity {
    private int reviewID;
    private int companyID;
    private String companyName;
    private int studentID;
    private int exercisesID;
    private String exercisesName;
}
