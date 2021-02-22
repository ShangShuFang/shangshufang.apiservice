package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ComprehensiveExercisesKnowledgeAnalysisEntity {
    private int analysisID;
    private int studentID;
    private int languageID;
    private String languageName;
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;
    private String result;
    private String resultText;
}
