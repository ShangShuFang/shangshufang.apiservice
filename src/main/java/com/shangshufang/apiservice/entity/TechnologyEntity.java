package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyEntity extends BaseEntity {
    private int technologyID;
    private String technologyName;
    private int technologyStars;
    private String technologyThumbnail;
    private String technologyMemo;
    private int knowledgeCount;
}
