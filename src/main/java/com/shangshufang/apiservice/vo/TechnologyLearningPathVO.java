package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class TechnologyLearningPathVO {
    private int technologyID;
    private String technologyName;
    private List<LearningPathVO> learningPhaseList;
}
