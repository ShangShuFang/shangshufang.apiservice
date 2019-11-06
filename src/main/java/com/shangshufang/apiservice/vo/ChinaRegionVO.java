package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class ChinaRegionVO {
    private int regionID;
    private int regionCode;
    private String regionName;
    private int regionParent;
}
