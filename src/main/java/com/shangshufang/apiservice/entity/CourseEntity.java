package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseEntity extends BaseEntity {
    private int courseID;
    private int universityCode;
    private String universityName;
    private int schoolID;
    private String schoolName;
    private int technologyID;
    private String technologyName;
    private String courseName;
    private int teacherID;
    private String teacherName;
    private String courseTimeBegin;
    private String courseTimeEnd;
    private String courseIntroduction;
    private float courseProcess;
    private int courseKnowledgeAcceptCompanyCount;
    private int courseKnowledgeTotalCount;
    private int courseKnowledgeFinishCount;
    private int courseExercisesCount;
    private int applyStudentCount;
    private int onlineQuestionTotalCount;
    private int onlineQuestionFinishCount;
}
