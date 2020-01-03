package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class LearningPathEntity extends BaseEntity {
    private int learningPathID;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    //private int knowledgeCount;
}
