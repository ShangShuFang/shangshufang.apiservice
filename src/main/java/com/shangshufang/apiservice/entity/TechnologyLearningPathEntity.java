package com.shangshufang.apiservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class TechnologyLearningPathEntity {
    private int technologyID;
    private String technologyName;
    private List<LearningPathEntity> learningPhaseEntityList;
}
