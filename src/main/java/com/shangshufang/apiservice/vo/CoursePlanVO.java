package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CoursePlanVO extends BaseVO {
    private int planID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int courseID;
    private int courseClass;
    private int learningPhaseID;
    private int technologyID;
    private String technologyName;
}
