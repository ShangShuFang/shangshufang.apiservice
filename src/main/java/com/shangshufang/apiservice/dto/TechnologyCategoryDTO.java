package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyCategoryDTO extends BaseDTO {
    private int technologyCategoryID;
    private String technologyCategoryName;
    private int directionID;
}
