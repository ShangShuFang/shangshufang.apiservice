package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class GrowingMapVO extends BaseVO {
    private int growingID;
    private String growingTarget;
    private String targetMemo;
    private List<GrowingMapDetailVO> growingMapDetailList;
}
