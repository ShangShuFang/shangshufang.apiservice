package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class VerificationCodeEntity extends BaseEntity {
    private int codeID;
    private String systemFunction;
    private String cellphone;
    private String code;
}
