package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyDirectionVO extends BaseVO {
    private int matchingID;
    private int technologyID;
    private String technologyName;
    private int directionID;
    private String directionName;
    private int directionStars;
    private String directionThumbnail;
    private String directionMemo;
}
