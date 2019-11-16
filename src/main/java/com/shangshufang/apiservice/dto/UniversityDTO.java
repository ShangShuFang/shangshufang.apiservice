package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class UniversityDTO extends BaseDTO {
    private int universityID;
    private int universityCode;
    private String universityName;
    private int provinceCode;
    private int cityCode;
    private int districtCode;
    private String address;
    private String brand;
    private String memo;
}
