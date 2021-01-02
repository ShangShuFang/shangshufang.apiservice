package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UserTrackingEntity extends BaseEntity {
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