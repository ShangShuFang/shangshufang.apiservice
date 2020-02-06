package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExercisesSingleVO extends BaseVO {
    private int exercisesID;
    private String  exercisesName;
    private String exercisesCode;
    private String exercisesType;
    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    private List<ExercisesDocumentVO> documentList;
}
