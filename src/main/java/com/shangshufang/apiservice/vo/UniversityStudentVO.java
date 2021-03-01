package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityStudentVO extends BaseVO {
    private int studentID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int majorID;
    private String majorName;

    private int educationLevel;
    private String educationLevelText;
    private String graduationDate;
    private String selfIntroductionUrl;

    private String fullName;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private String cellphone;
    private String password;
    private String email;
    private String photo;
}
