package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class GrowingMapDetailEntity extends BaseEntity {
    private int growingDetailID;
    private int growingID;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
}
