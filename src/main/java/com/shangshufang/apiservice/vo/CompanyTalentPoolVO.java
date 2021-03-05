package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CompanyTalentPoolVO extends BaseVO {
    private int talentID;
    private int companyID;
    private String companyName;
    private int studentID;
    private String studentName;
    private String sex;
    private String universityName;
    private String majorName;
    private String cellphone;
    private String email;
    private String interviewJob;
    private String interviewTime;
    private String interviewAddress;
    private String memo;
}
