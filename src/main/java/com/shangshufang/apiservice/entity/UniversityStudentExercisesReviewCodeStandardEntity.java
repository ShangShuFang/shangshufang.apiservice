package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityStudentExercisesReviewCodeStandardEntity extends BaseEntity {
    private int codeStandardReviewID;
    private int reviewID;
    private int studentUniversityCode;
    private int studentSchoolID;
    private int studentID;
    private int technologyID;
    private int codeStandardID;
    private String codeStandardName;
}
