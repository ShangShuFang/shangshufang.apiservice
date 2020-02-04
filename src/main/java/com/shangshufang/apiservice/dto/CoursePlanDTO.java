package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CoursePlanDTO extends BaseDTO {
    private int universityCode;
    private int schoolID;
    private int courseID;
    private int courseClass;
}
