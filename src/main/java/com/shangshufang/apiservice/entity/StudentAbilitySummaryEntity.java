package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentAbilitySummaryEntity {
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int majorID;
    private String majorName;
    private int studentID;
    private String fullName;
    private String photo;
    private String cellphone;
    private String email;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private int onlineQuestionCount;
    private int onlineAnswerCount;
    private int joinProjectCount;
}
