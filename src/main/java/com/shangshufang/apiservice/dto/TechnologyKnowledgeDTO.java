package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyKnowledgeDTO extends BaseDTO {
    private int knowledgeID;
    private int technologyID;
    private String knowledgeName;
}
