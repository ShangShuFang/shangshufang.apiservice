package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class SuggestVO extends BaseVO {
    private int suggestID;
    private int suggestTypeID;
    private String suggestType;
    private String suggestContent;
    private String cellphone;
    private String portal;
    private String portalText;
    private String memo;
}
