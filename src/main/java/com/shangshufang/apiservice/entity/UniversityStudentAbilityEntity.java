package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityStudentAbilityEntity {
    private int studentID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private String fullName;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private String cellphone;
    private String email;
    private String photo;
    private int onlineQuestionCount;
    private int onlineAnswerCount;
    private int joinProjectCount;
}