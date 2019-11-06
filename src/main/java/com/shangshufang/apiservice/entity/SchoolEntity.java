package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class SchoolEntity extends BaseEntity {
    private int schoolID;
    private String schoolName;
    private int universityCode;
    private String universityName;
    private String contacts;
    private String cellphone;
}
