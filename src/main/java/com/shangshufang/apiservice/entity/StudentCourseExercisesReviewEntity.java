package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesReviewEntity extends BaseEntity {
    private int reviewID;
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private int courseExercisesDetailID;
    private String correctResult;
}
