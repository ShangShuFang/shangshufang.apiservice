package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseExercisesVO extends BaseVO {
    private int planID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int courseID;
    private int courseName;
    private int technologyID;
    private String technologyName;
    private int courseClass;
    private List<CourseExercisesKnowledgeVO> knowledgeList;

//    private int courseClass;
//    private int exercisesID;
//    private String  exercisesName;
//    private String exercisesCode;
//    private String exercisesType;
//    private int technologyID;
//    private String technologyName;
//    private int learningPhaseID;
//    private String learningPhaseName;
//    private int knowledgeID;
//    private String knowledgeName;
//    private List<ExercisesDocumentVO> documentList;
}
