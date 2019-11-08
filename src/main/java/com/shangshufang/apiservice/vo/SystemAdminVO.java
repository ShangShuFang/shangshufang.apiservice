package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class SystemAdminVO extends BaseVO {
    private int adminID;
    private String adminName;
    private String cellphone;
    private String password;
}
