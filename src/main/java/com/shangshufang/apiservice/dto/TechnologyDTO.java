package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyDTO extends BaseDTO {
    private int technologyID;
    private String technologyName;
    private int technologyStars;
    private String technologyThumbnail;
    private String technologyMemo;
}
