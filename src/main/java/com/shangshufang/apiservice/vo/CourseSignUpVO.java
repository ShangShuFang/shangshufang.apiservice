package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CourseSignUpVO extends BaseVO {
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
