package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityStudentDTO extends BaseDTO {
    private int studentID;
    private int universityCode;
    private int schoolID;
    private String fullName;
    private String sex;
    private String birth;
    private String enrollmentYear;
    private String cellphone;
    private String password;
    private String email;
    private String photo;
}
