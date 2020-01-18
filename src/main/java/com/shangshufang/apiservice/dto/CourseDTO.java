package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseDTO extends BaseDTO {
    private int courseID;
    private int universityCode;
    private int schoolID;
    private int technologyID;
    private String courseName;
    private int teacherID;
    private String courseTimeBegin;
    private String courseTimeEnd;
    private String courseIntroduction;
    private String courseScheduleJson;
    private String coursePlanJson;
}
