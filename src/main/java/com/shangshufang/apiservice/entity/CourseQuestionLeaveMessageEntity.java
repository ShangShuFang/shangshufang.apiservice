package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class CourseQuestionLeaveMessageEntity extends BaseEntity {
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
