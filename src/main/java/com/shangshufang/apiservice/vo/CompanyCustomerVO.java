package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CompanyCustomerVO extends BaseVO {
    private int customerID;
    private int companyID;
    private String fullName;
    private String sex;
    private String birth;
    private String cellphone;
    private String email;
    private String photo;
}
