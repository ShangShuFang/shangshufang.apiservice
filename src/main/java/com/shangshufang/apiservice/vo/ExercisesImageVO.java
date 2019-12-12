package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ExercisesImageVO extends BaseVO {
    private int exercisesImageID;
    private int exercisesID;
    private String imageUrl;
}
