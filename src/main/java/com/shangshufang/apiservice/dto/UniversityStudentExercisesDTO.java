package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityStudentExercisesDTO extends BaseDTO {
    private int studentExercisesID;
    private int courseUniversityCode;
    private int courseSchoolID;
    private int courseID;
    private int courseClass;

    private int teacherID;
    private boolean containCompanyExercises;
    private boolean containSelfExercises;
    private boolean containOtherExercises;
    private int maxChoiceCount;
    private int maxBlankCount;
    private int maxProgramCount;

//    private int assignCount;
    private String sourceCodeGitUrl;
}
