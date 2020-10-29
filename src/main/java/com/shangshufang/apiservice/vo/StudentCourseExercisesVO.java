package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentCourseExercisesVO extends BaseVO {
    private int courseExercisesID;
    private int courseID;
    private int courseClass;
    private int studentID;
    private String studentName;
}
