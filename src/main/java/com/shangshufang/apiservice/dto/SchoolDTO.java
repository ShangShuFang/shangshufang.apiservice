package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class SchoolDTO extends BaseDTO {
    private int schoolID;
    private String schoolName;
    private int universityCode;
    private String contacts;
    private String cellphone;
}
