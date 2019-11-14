package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CompanyAccountEntity extends BaseEntity {
    private int accountID;
    private int companyID;
    private String companyName;
    private int customerID;
    private String customerName;
    private String cellphone;
    private String email;
    private String password;
    private String accountRole;
    private String accountRoleText;
    private String photo;
}
