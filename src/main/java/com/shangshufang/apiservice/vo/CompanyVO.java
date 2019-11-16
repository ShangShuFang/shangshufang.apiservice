package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class CompanyVO extends BaseVO {
    private int companyID;
    private String companyName;
    private int provinceCode;
    private String provinceName;
    private int cityCode;
    private String cityName;
    private int districtCode;
    private String districtName;
    private String address;
    private String contacts;
    private String cellphone;
    private String businessLicense;
    private String brand;
    private String memo;
}
