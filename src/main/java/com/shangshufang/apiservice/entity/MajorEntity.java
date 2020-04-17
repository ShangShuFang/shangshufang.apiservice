package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class MajorEntity extends BaseEntity {
    private int majorID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private String majorName;

}
