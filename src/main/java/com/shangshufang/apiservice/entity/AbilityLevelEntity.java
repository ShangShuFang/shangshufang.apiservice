package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class AbilityLevelEntity {
    private int levelID;
    private String levelCode;
    private String levelName;
    private String requiredKnowledge;
    private String requiredComprehensiveExercises;
    private String requiredProjectExercises;
}
