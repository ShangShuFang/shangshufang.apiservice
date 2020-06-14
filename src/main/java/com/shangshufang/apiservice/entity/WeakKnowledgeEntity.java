package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class WeakKnowledgeEntity {
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;
    private int reviewCount;
}
