package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentKnowledgeAnalysisEntity {
    private int studentUniversityCode;
    private int studentSchoolID;
    private int studentID;
    private int technologyID;

    private int technologyKnowledgeCount;
    private int noLearningKnowledgeCount;
    private int weaknessKnowledgeCount;
    private int graspKnowledgeCount;
}
