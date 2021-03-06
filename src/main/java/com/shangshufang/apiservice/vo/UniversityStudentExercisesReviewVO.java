package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class UniversityStudentExercisesReviewVO extends BaseVO {
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
    private List<UniversityStudentExercisesReviewCodeStandardVO> codeStandardErrorList;
    private String reviewResult;
    private String reviewResultText;
    private String reviewMemo;
}
