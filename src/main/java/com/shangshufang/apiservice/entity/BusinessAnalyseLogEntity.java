package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class BusinessAnalyseLogEntity extends BaseEntity {
    private int logID;
    private String cityIP;
    private String cityID;
    private String cityName;
    private String browser;
    private String portal;
    private String device;
    private String pageName;
    private String operationName;
    private String operationResult;
    private String operationType;
    private String memo;
    private int customerID;
    private String customerName;
}
