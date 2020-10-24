package com.shangshufang.apiservice.vo;

import lombok.Data;
import java.util.List;

@Data
public class UniversityExerciseKnowledgeChoiceVO extends BaseVO {
    private int exercisesID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int teacherID;
    private String teacherName;
    private int technologyID;
    private int knowledgeID;
    private String exercisesTitle;
    private String exercisesType;
    private List<UniversityExerciseKnowledgeChoiceOptionVO> choiceOptions;
}
