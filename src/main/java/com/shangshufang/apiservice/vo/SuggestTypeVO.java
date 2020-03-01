package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class SuggestTypeVO extends BaseVO {
    private int suggestTypeID;
    private String suggestType;
    private String portal;
}
