package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityVO extends BaseVO {
    private int universityID;
    private int universityCode;
    private String universityName;
    private int provinceCode;
    private String provinceName;
    private int cityCode;
    private String cityName;
    private int districtCode;
    private String districtName;
    private String address;
}
