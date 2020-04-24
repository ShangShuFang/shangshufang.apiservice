package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityStudentEntity extends BaseEntity {
    private int studentID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int majorID;
    private String majorName;
    private String fullName;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private String cellphone;
    private String password;
    private String email;
    private String photo;
    private int assistant;
}
