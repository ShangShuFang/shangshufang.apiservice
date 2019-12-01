package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class LearningPathDTO extends BaseDTO {
    private int learningPathID;
    private int technologyID;
    private int learningPhaseID;
    private String knowledgeIdList;
}
