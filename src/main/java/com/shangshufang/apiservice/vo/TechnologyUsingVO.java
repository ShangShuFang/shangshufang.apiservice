package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyUsingVO extends BaseVO {
    private int usingID;
    private int companyID;
    private String companyName;
    private String companyLogo;
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailSquare;
    private String technologyThumbnailRectangle;
    private String recruitLevel;
}
