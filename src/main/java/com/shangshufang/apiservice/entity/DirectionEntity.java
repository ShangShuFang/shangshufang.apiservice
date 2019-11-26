package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class DirectionEntity extends BaseEntity {
    private int directionID;
    private String directionName;
    private int directionStars;
    private String directionThumbnail;
    private String directionMemo;
    private int technologyCount;
}
