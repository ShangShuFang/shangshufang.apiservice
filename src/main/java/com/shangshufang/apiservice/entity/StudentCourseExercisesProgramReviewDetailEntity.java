package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesProgramReviewDetailEntity extends BaseEntity {
    private int programReviewID;
    private int reviewID;
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private int courseExercisesDetailID;
    private String compilationResult;
    private String runResult;
    private String codeStandardResult;
    private String reviewResult;
    private String reviewMemo;
}
