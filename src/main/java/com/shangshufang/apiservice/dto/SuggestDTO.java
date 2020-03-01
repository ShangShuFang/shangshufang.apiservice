package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class SuggestDTO extends BaseDTO {
    private int suggestID;
    private int suggestTypeID;
    private String suggestContent;
    private String cellphone;
    private String portal;
}
