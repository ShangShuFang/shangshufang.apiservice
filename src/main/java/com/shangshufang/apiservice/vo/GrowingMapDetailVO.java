package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class GrowingMapDetailVO {
    private int growingDetailID;
    private int growingID;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private List<LearningPathVO> knowledgeList;
}
