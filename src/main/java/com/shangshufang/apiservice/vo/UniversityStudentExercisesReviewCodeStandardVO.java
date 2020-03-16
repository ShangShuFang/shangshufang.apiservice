package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityStudentExercisesReviewCodeStandardVO extends BaseVO {
    private int codeStandardReviewID;
    private int reviewID;
    private int studentUniversityCode;
    private int studentSchoolID;
    private int studentID;
    private int technologyID;
    private int codeStandardID;
    private String codeStandardName;
}
