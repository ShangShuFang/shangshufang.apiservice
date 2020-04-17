package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class MajorVO extends BaseVO {
    private int majorID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private String majorName;
}
