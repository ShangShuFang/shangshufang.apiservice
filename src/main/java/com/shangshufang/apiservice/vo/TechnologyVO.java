package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyVO extends BaseVO {
    private int technologyID;
    private String technologyName;
    private int technologyStars;
    private String technologyThumbnail;
    private String technologyMemo;
}
