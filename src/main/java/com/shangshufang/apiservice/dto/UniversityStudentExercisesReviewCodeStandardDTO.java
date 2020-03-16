package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityStudentExercisesReviewCodeStandardDTO extends BaseDTO {
    private int codeStandardReviewID;
    private int reviewID;
    private int studentUniversityCode;
    private int studentSchoolID;
    private int studentID;
    private int technologyID;
    private int codeStandardID;
}
