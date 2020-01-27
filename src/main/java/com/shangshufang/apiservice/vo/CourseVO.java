package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseVO extends BaseVO {
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
    private int courseTechnologyUsingCompanyCount;
    private int courseKnowledgeCount;
    private int technologyKnowledgeCount;
    private int courseKnowledgeExercisesCount;
    private int courseSingUpCount;
    private int courseQuestionFinish;
    private int courseQuestion;
    private String courseProcess;
    private List<CourseScheduleVO> courseScheduleList;
    private List<CoursePlanVO> coursePlanList;
}
