package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UserTrackingDTO extends BaseDTO {
    private int trackingID;
    private String cityIP;
    private String cityID;
    private String cityName;
    private String customer;
    private String device;
    private String browser;
    private int systemID;
    private int viewID;
}