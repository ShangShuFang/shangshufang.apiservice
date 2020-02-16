package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseQuestionDTO extends BaseDTO {
    private int questionID;
    private int courseUniversityCode;
    private int courseSchoolID;
    private int courseID;
    private int questionerUniversityCode;
    private int questionerSchoolID;
    private int questionerID;
    private String questionContent;
}
