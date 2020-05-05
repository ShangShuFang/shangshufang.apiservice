package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyCategoryVO extends BaseVO {
    private int technologyCategoryID;
    private String technologyCategoryName;
    private int directionID;
    private String directionName;
}
