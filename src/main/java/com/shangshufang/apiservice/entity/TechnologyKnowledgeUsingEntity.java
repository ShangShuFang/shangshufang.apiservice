package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyKnowledgeUsingEntity extends BaseEntity {
    private int usingID;
    private int companyID;
    private String companyName;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
}
