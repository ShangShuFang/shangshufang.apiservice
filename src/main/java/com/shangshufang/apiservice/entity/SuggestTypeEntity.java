package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class SuggestTypeEntity extends BaseEntity{
    private int suggestTypeID;
    private String suggestType;
    private String portal;
}
