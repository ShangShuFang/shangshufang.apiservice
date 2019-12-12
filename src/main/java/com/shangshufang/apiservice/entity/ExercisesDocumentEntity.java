package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExercisesDocumentEntity extends BaseEntity {
    private int exercisesDocumentID;
    private int exercisesID;
    private String documentUrl;
}
