package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseScheduleEntity extends BaseEntity {
    private int scheduleID;
    private int universityCode;
    private int schoolID;
    private int courseID;
    private int weekday;
    private int weekdayClass;
    private String weekdayClassTime;
}
