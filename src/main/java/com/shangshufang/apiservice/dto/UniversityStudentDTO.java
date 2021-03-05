package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityStudentDTO extends BaseDTO {
    private int studentID;
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int educationLevel;
    private String fullName;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private String graduationDate;
    private String cellphone;
    private String password;
    private String email;
    private String photo;
    private String selfIntroductionUrl;
}
