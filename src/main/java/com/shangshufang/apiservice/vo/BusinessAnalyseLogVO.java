package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class BusinessAnalyseLogVO extends BaseVO {
    private int logID;
    private String cityIP;
    private String cityID;
    private String cityName;
    private String browser;
    private String portal;
    private String device;
    private String pageName;
    private String operation;
    private String memo;
    private int customerID;
    private String customerName;
}
