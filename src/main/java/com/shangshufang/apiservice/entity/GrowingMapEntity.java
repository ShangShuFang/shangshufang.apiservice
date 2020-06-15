package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class GrowingMapEntity extends BaseEntity {
    private int growingID;
    private String growingTarget;
    private String targetMemo;
}
