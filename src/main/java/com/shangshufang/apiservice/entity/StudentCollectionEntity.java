package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class StudentCollectionEntity extends BaseEntity {
    private int collectionID;
    private int studentID;
    private int companyID;
    private String companyName;
    private String companyAbbreviation;
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
    private int usingTechnologyCount;
}
