package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseQuestionVO extends BaseVO {
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
    private List<CourseQuestionLeaveMessageVO> leaveMessageList;
}
