package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class CompanyCollectionDTO extends BaseDTO {
    private int collectionID;
    private int companyID;
    private int studentID;
}
