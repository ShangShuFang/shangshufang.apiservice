package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class BusinessAnalyseLogDTO extends BaseDTO {
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
}
