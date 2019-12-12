package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ExercisesFileDTO extends BaseDTO {
    private int exercisesID;
    private String imageList;
    private String documentList;
}
