package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CompanyAccountVO extends BaseVO {
    private int accountID;
    private int companyID;
    private String companyName;
    private int customerID;
    private String customerName;
    private String cellphone;
    private String email;
    private String accountRole;
    private String accountRoleText;
    private String photo;
}
