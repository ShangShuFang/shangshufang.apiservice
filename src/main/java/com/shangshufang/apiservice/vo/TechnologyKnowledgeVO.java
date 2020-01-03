package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyKnowledgeVO extends BaseVO {
    private int knowledgeID;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private String knowledgeName;
    private int usingCompanyCount;
}
