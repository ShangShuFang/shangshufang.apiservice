package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class LearningPathVO extends BaseVO {
    private int learningPathID;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    //private int knowledgeCount;
}
