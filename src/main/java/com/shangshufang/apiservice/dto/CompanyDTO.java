package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CompanyDTO extends BaseDTO {
    private int companyID;
    private String companyName;
    private String companyAbbreviation;
    private int provinceCode;
    private int cityCode;
    private int districtCode;
    private String address;
    private String contacts;
    private String cellphone;
    private String businessLicense;
    private String brand;
    private String recruitLevel;
    private String memo;
}
