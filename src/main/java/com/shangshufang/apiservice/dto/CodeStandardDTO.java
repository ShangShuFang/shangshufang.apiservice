package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CodeStandardDTO extends BaseDTO {
    private int codeStandardID;
    private int languageID;
    private String codeStandardName;
}
