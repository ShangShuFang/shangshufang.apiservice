package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class ThirdPartyServicesDTO extends BaseDTO {
    private int serviceType;
    private String requestContent;
    private String responseContent;
    private String result;
}
