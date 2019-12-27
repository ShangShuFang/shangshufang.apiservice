package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class VerificationCodeDTO extends BaseDTO {
    private String systemFunction;
    private String cellphone;
    private String code;
}
