package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class UniversityEntity extends BaseEntity {
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
    private String brand;
    private String memo;
}
