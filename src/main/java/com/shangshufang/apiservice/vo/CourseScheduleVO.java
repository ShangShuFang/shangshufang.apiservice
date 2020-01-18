package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CourseScheduleVO extends BaseVO {
    private int scheduleID;
    private int universityCode;
    private int schoolID;
    private int courseID;
    private int weekday;
    private int weekdayClass;
    private String weekdayClassTime;
}
