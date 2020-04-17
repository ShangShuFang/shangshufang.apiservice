package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CodeStandardVO extends BaseVO {
    private int codeStandardID;
    private int languageID;
    private String languageName;
    private String codeStandardName;
}
