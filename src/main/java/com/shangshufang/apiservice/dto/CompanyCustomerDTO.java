package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CompanyCustomerDTO extends BaseDTO {
    private int customerID;
    private int companyID;
    private String fullName;
    private String sex;
    private String birth;
    private String cellphone;
    private String email;
}
