package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class SystemAdminEntity extends BaseEntity {
    private int adminID;
    private String adminName;
    private String cellphone;
    private String password;
}
