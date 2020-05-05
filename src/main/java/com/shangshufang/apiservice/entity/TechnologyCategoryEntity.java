package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyCategoryEntity extends BaseEntity {
    private int technologyCategoryID;
    private String technologyCategoryName;
    private int directionID;
    private String directionName;
}
