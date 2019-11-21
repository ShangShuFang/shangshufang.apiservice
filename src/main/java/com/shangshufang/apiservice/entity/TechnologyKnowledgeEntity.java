package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyKnowledgeEntity extends BaseEntity {
    private int knowledgeID;
    private int technologyID;
    private String technologyName;
    private String knowledgeName;
}
