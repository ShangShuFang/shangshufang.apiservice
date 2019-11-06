package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ChinaRegionEntity {
    private int regionID;
    private int regionCode;
    private String regionName;
    private int regionParent;
}
