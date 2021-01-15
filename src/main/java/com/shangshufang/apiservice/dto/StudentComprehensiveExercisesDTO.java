package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class StudentComprehensiveExercisesDTO extends BaseDTO {
    private int collectionID;
    private int studentID;
    private int exercisesID;
    private String gitUrl;
    private String reviewMemo;
    private int programLanguage;
}
