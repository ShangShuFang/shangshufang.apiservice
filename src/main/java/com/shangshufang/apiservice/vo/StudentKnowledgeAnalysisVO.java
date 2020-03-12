package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class StudentKnowledgeAnalysisVO {
    private int technologyKnowledgeCount;
    private int noLearningKnowledgeCount;
    private int weaknessKnowledgeCount;
    private int graspKnowledgeCount;
    private float learningPercentCount;
}
