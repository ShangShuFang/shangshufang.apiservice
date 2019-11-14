package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CompanyCustomerEntity extends BaseEntity {
    private int customerID;
    private int companyID;
    private String companyName;
    private String fullName;
    private String sex;
    private String birth;
    private String cellphone;
    private String email;
    private String photo;
}
