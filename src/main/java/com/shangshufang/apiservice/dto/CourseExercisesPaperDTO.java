package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CourseExercisesPaperDTO extends BaseDTO {
    private int studentID;
    private int courseID;
    private int courseClass;
    private int courseExercisesID;
    private String singleChoiceListJson;
    private String multipleChoiceListJson;
    private String blankChoiceListJson;
    private String programChoiceListJson;
}