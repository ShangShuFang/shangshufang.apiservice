package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityStudentExercisesReviewDTO extends BaseDTO {
    private int reviewID;

    private int courseUniversityCode;
    private int courseSchoolID;
    private int courseID;
    private int courseClass;

    private int studentExercisesID;
    private int reviewerID;
    private int reviewerUniversityCode;
    private int reviewerSchoolID;
    private String reviewerType;
    private String compilationResult;
    private String runResult;
    private String codeStandardResult;
    private String codeStandardErrorListJson;
    private String reviewResult;
    private String reviewMemo;
}
