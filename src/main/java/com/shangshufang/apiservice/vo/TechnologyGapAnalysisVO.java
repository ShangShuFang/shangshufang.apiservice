package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class TechnologyGapAnalysisVO {
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailRectangle;

    private String recruitLevel;
    private String requiredKnowledge;
    private String requiredComprehensiveExercises;
    private String requiredProjectExercises;

    private String studentLevel;
    private float finishKnowledgePercent;
    private int finishComprehensiveExercisesCount;
    private int joinProjectExercisesCount;
}
