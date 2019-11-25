package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class DirectionDTO extends BaseDTO {
    private int directionID;
    private String directionName;
    private int directionStars;
    private String directionThumbnail;
    private String directionMemo;
}
