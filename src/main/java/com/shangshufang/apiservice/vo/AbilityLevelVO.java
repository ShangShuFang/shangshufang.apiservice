package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class AbilityLevelVO {
    private int levelID;
    private String levelCode;
    private String levelName;
    private String requiredKnowledge;
    private String requiredComprehensiveExercises;
    private String requiredProjectExercises;
}
