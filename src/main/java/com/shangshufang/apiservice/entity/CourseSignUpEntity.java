package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseSignUpEntity extends BaseEntity {
    private int signUpID;
    private int studentUniversityCode;
    private String studentUniversityName;
    private int studentSchoolID;
    private String studentSchoolName;
    private int studentID;
    private String studentName;
    private String studentPhoto;
    private String cellphone;
    private String enrollmentYear;
    private int courseUniversityCode;
    private String courseUniversityName;
    private int courseSchoolID;
    private String courseSchoolName;
    private int courseID;
    private String courseName;
}
