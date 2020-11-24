package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CodeGuidelineSummaryVO {
    private int studentID;
    private int languageID;
    private int codeStandardID;
    private String codeStandardName;
    private int totalCount;
}
