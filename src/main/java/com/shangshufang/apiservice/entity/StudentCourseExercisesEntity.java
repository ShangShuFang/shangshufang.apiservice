package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCourseExercisesEntity extends BaseEntity {
    private int courseExercisesID;
    private int languageID;
    private int technologyID;
    private String technologyName;
    private int universityCode;
    private String universityName;
    private int courseID;
    private String courseName;
    private int courseClass;
    private int studentID;
    private String studentName;
}
