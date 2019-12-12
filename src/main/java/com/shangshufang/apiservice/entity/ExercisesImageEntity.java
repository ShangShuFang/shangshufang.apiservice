package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ExercisesImageEntity extends BaseEntity {
    private int exercisesImageID;
    private int exercisesID;
    private String imageUrl;
}
