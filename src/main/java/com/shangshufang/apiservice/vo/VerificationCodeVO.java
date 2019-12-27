package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class VerificationCodeVO extends BaseVO {
    private int codeID;
    private String systemFunction;
    private String cellphone;
    private String code;
}
