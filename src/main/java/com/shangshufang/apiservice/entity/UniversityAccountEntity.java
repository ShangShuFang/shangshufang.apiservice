package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityAccountEntity extends BaseEntity {
    private int accountID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int customerID;
    private String customerName;
    private String cellphone;
    private String password;
    private String email;
    private String accountRole;
}
