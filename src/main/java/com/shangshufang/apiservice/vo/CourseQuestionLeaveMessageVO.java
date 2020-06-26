package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CourseQuestionLeaveMessageVO extends BaseVO {
    private int messageID;
    private int questionID;

    private int questionerID;
    private String questionerName;
    private String courseName;
    private String questionContent;

    private int commenterUniversityCode;
    private String commenterUniversityName;
    private int commenterSchoolID;
    private String commenterSchoolName;
    private int commenterID;
    private String commenterName;
    private String commenterPhoto;
    private String commenterType;
    private String commenterTypeText;

    private String messageContent;
}
