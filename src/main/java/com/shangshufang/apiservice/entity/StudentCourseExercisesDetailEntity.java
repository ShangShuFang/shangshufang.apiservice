package com.shangshufang.apiservice.entity;

import lombok.Data;
import java.util.List;

@Data
public class StudentCourseExercisesDetailEntity extends BaseEntity {
    private int courseExercisesDetailID;
    private int courseExercisesID;
    private int technologyID;
    private String technologyName;
    private int knowledgeID;
    private String knowledgeName;
    private int exercisesID;
    private String exercisesTitle;
    private int exercisesType;
    private int exercisesSourceType;
    private String exercisesSource;
    private String programExercisesCodeUri;
    private String correctResult;
    private String corrector;
    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> choiceOptionEntityList;
}
