package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityAccountVO extends BaseVO {
    private int accountID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int customerID;
    private String customerName;
    private String cellphone;
    private String email;
    private String photo;
    private String accountRole;
    private String accountRoleText;
}
