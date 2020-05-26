package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseSignUpDTO extends BaseDTO {
    private int signUpID;
    private int studentUniversityCode;
    private int studentSchoolID;
    private int studentID;
    private int courseUniversityCode;
    private int courseSchoolID;
    private int courseID;
    private int assistant;
}
