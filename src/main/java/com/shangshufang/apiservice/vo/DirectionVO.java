package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class DirectionVO extends BaseVO {
    private int directionID;
    private String directionName;
    private int directionStars;
    private String directionThumbnail;
    private String directionMemo;
    private int involvedTechnologyCount;
}
