package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class TechnologyVO extends BaseVO {
    private int technologyID;
    private String technologyName;
    private int technologyStars;
    private String technologyThumbnail;
    private String technologyMemo;
    private int knowledgeCount;
    private int directionID;
    private String directionName;
    private int categoryID;
    private String categoryName;
    private int usingCompanyCount;
    private String difficultyLevel;
    private String difficultyLevelText;
    private int languageID;
    private String languageName;
    List<AbilityAnalysisResult4StudentMainInfoVO> topStudentMainInfoList;
}
