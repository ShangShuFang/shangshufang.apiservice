package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityAnalysisResult4KnowledgeSummaryEntity {
    private int universityCode;
    private int schoolID;
    private int majorID;
    private int studentID;
    private int technologyID;
    private int knowledgeTotalCount;
    private int weaknessKnowledgeCount;
    private int graspKnowledgeCount;
    private int noLearningKnowledgeCount;
    private float learningPercentCount;
}
