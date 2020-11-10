package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesBlankAnswerEntity extends BaseEntity {
    private int answerID;
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private int courseExercisesDetailID;
    private String fillInContent;
    private String correctResult;
}
