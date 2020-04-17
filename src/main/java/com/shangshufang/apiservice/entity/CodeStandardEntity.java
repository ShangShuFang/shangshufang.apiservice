package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CodeStandardEntity extends BaseEntity {
    private int codeStandardID;
    private int languageID;
    private String languageName;
    private String codeStandardName;
}
