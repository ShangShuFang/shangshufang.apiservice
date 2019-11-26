package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyDirectionEntity extends BaseEntity {
    private int matchingID;
    private int technologyID;
    private String technologyName;
    private int directionID;
    private String directionName;
    private int directionStars;
    private String directionThumbnail;
    private String directionMemo;
}
