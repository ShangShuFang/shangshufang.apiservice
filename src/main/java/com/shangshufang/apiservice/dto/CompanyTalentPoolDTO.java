package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CompanyTalentPoolDTO extends BaseDTO {
    private int talentID;
    private int companyID;
    private int studentID;
    private String interviewTime;
    private String interviewAddress;
    private String memo;
}
