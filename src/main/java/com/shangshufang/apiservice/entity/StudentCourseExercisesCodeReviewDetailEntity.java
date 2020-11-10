package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesCodeReviewDetailEntity extends BaseEntity {
    private int codeReviewID;
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private int courseExercisesDetailID;
    private int languageID;
    private int codeStandardID;
}
