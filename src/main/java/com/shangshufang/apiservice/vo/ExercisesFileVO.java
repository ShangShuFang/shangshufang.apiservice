package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExercisesFileVO {
    private int exercisesID;
    private List<ExercisesImageVO> imageList;
    private List<ExercisesDocumentVO> documentList;
}
