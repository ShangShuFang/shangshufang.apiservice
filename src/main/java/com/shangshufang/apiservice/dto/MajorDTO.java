package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class MajorDTO extends BaseDTO {
    private int majorID;
    private int universityCode;
    private int schoolID;
    private String majorName;

}
