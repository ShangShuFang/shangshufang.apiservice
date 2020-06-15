package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class GrowingMapDTO extends BaseDTO {
    private int growingID;
    private String growingTarget;
    private String targetMemo;
    private String detailJson;
}
