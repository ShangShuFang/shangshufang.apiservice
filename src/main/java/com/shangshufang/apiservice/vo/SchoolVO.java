package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class SchoolVO extends BaseVO {
    private int schoolID;
    private String schoolName;
    private int universityCode;
    private String universityName;
    private String contacts;
    private String cellphone;
}
