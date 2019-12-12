package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ExercisesDTO extends BaseDTO {
    private int exercisesID;
    private String  exercisesName;
    private String exercisesCode;
    private String exercisesType;
    private String exercisesAnswer;
    private String knowledgeListJson;
    private String imageListJson;
    private String documentListJson;
}
