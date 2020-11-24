package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CodeGuidelineSummaryEntity {
    private int studentID;
    private int languageID;
    private int codeStandardID;
    private String codeStandardName;
    private int totalCount;
}
