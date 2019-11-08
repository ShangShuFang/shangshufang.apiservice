package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityCustomerEntity extends BaseEntity {
    private int customerID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private String fullName;
    private String sex;
    private String birth;
    private String cellphone;
    private String email;
    private String photo;
}
