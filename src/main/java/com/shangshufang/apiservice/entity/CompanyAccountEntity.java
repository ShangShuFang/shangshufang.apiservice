package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CompanyAccountEntity extends BaseEntity {
    private int customerID;
    private int companyID;
    private String companyName;
    private String customerName;
    private String cellphone;
    private String email;
    private String password;
    private String photo;
}
