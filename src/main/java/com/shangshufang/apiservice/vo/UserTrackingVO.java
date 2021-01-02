package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UserTrackingVO extends BaseVO {
    private int trackingID;
    private String cityIP;
    private String cityID;
    private String cityName;
    private String customer;
    private String device;
    private String browser;
    private int systemID;
    private String systemName;
    private int viewID;
    private String viewName;
}