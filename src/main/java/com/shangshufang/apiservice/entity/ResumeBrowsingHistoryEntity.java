package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ResumeBrowsingHistoryEntity extends BaseEntity {
    private int browseID;
    private int companyID;
    private String companyName;
    private int studentID;
    private String studentName;
}
