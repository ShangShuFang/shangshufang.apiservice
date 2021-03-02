package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CompanyTalentPoolEntity extends BaseEntity {
    private int talentID;
    private int companyID;
    private int studentID;
    private String studentName;
    private String sex;
    private String universityName;
    private String majorName;
    private String cellphone;
    private String email;
    private String interviewTime;
    private String interviewAddress;
    private String memo;
}
