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
    private int classExercisesTotalCount;
    private List<CourseExercisesKnowledgeVO> knowledgeList;
}
