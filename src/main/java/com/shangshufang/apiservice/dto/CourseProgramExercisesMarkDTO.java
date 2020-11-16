package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseProgramExercisesMarkDTO extends BaseDTO {
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private int courseExercisesDetailID;
    private String compilationResult;
    private String runResult;
    private String codeStandardResult;
    private String reviewResult;
    private String reviewMemo;
    private String codeStandardErrorListJson;
}
