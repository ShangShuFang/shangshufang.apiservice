package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ExercisesDocumentVO extends BaseVO {
    private int exercisesDocumentID;
    private int exercisesID;
    private String documentUrl;
    private String answerUrl;
}
