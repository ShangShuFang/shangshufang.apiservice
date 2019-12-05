package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyKnowledgeUsingDTO extends BaseDTO {
    private int companyID;
    private int technologyID;
    private int learningPhaseID;
    private String knowledgeIdList;
}
