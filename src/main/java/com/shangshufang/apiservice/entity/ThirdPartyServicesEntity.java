package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class ThirdPartyServicesEntity extends BaseEntity {
    private int logID;
    private int serviceType;
    private String requestContent;
    private String responseContent;
    private String result;
}
