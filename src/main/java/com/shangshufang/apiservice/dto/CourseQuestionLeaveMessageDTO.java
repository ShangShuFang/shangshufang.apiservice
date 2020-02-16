package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseQuestionLeaveMessageDTO extends BaseDTO {
    private int messageID;
    private int questionID;
    private int commenterUniversityCode;
    private int commenterSchoolID;
    private int commenterID;
    private String commenterType;
    private String messageContent;
}
