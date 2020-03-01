package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class SuggestEntity extends BaseEntity{
    private int suggestID;
    private int suggestTypeID;
    private String suggestType;
    private String suggestContent;
    private String cellphone;
    private String portal;
    private String portalText;
    private String memo;
}
