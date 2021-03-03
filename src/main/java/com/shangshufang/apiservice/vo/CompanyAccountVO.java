package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CompanyAccountVO extends BaseVO {
    private int customerID;
    private int companyID;
    private String companyName;
    private String customerName;
    private String cellphone;
    private String email;
    private String password;
    private String photo;
}
