package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class LearningPhaseEntity extends BaseEntity{
    private int learningPhaseID;
    private int technologyID;
    private String learningPhaseName;
}
