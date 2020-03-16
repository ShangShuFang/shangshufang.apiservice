package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityStudentExercisesReviewEntity extends BaseEntity {
    private int reviewID;
    private int studentExercisesID;
    private int reviewerID;
    private String reviewerName;
    private String reviewerTeacherPhoto;
    private String reviewerStudentPhoto;
    private int reviewerUniversityCode;
    private String reviewerUniversityName;
    private int reviewerSchoolID;
    private String reviewerSchoolName;
    private String reviewerType;
    private String reviewerTypeText;
    private String compilationResult;
    private String compilationResultText;
    private String runResult;
    private String runResultText;
    private String codeStandardResult;
    private String codeStandardResultText;
    private String reviewResult;
    private String reviewResultText;
    private String reviewMemo;
}
