package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseQuestionEntity extends BaseEntity {
    private int questionID;
    private int courseUniversityCode;
    private int courseSchoolID;
    private int courseID;
    private int questionerUniversityCode;
    private String questionerUniversityName;
    private int questionerSchoolID;
    private String questionerSchoolName;
    private int questionerID;
    private String questionerName;
    private String questionerPhoto;
    private String questionContent;
}
