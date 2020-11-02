package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesEntity extends BaseEntity {
    private int courseExercisesID;
    private int courseID;
    private String courseName;
    private int courseClass;
    private int studentID;
    private String studentName;
}
