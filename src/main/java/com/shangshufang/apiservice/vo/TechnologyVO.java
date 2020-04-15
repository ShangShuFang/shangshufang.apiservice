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
    private int directionCount;
    private int usingCompanyCount;
    private String directionNames;
    private int languageID;
    private String languageName;
    List<AbilityAnalysisResult4StudentMainInfoVO> topStudentMainInfoList;
}
