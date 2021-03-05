package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ResumeBrowsingHistoryVO extends BaseVO {
    private int companyID;
    private String companyName;
    private int studentID;
    private String studentName;
}
