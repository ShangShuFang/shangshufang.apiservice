package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CompanyAccountDTO extends BaseDTO {
    private int accountID;
    private int companyID;
    private int customerID;
    private String fullName;
    private String cellphone;
    private String password;
    private String accountRole;
}
