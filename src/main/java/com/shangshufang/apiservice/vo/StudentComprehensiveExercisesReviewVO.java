package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesReviewVO extends BaseVO {
    private int reviewID;
    private int companyID;
    private String companyName;
    private int studentID;
    private int exercisesID;
    private String exercisesName;
}
