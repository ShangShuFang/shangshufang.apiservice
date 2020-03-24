package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityAccountDTO extends BaseDTO {
    private int accountID;
    private int universityCode;
    private int schoolID;
    private int customerID;
    private int studentID;
    private String fullName;
    private String cellphone;
    private String password;
    private String accountRole;
}
