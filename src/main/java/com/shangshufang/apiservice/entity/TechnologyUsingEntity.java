package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyUsingEntity extends BaseEntity {
    private int usingID;
    private int companyID;
    private String companyName;
    private int technologyID;
    private String technologyName;
    private String technologyThumbnailSquare;
    private String technologyThumbnailRectangle;
    private String recruitLevel;

    private String requiredKnowledge;
    private String requiredComprehensiveExercises;
    private String requiredProjectExercises;
}
