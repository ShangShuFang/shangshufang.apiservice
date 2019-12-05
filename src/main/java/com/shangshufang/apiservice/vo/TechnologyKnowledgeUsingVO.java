package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyKnowledgeUsingVO extends BaseVO {
    private int companyID;
    private String companyName;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
}
