package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CourseKnowledgeLearnAnalyseVO {
    private int courseID;
    private int courseClass;
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;

    private int pendingCount;
    private float pendingPercent;

    private int correctingCount;
    private float correctingPercent;

    private int learnedCount;
    private float learnedPercent;

    private int weaknessCount;
    private float weaknessPercent;
}
