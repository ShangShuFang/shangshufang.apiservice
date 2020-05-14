package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyDTO extends BaseDTO {
    private int technologyID;
    private int languageID;
    private String technologyName;
    private int technologyStars;
    private String technologyThumbnailSquare;
    private String technologyThumbnailRectangle;
    private String technologyMemo;
    private int directionID;
    private int categoryID;
    private String difficultyLevel;
}
