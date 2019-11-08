package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityCustomerDTO extends BaseDTO {
    private int customerID;
    private int universityCode;
    private int schoolID;
    private String fullName;
    private String sex;
    private String birth;
    private String cellphone;
    private String email;
    private String photo;
}
