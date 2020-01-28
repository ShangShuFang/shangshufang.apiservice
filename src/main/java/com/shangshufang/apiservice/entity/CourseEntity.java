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
    private String teacherPhotoUrl;
    private String courseTimeBegin;
    private String courseTimeEnd;
    private String courseIntroduction;
    private int courseTechnologyUsingCompanyCount;
    private int courseKnowledgeCount;
    private int technologyKnowledgeCount;
    private int courseKnowledgeExercisesCount;
    private int courseSingUpCount;
    private int courseQuestionFinish;
    private int courseQuestion;
    private String courseProcess;
    private int courseKnowledgeFinishCount;
}
