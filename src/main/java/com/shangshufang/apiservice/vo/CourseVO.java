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
    private int technologyName;
    private String courseName;
    private int teacherID;
    private int teacherName;
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
    private List<CourseScheduleVO> courseScheduleList;
    private List<CoursePlanVO> coursePlanList;
}
