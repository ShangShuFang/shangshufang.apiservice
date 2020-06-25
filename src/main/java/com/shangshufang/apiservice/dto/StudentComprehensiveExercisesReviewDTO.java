package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesReviewDTO extends BaseDTO {
    private int reviewID;
    private int companyID;
    private int studentID;
    private int exercisesID;
}
