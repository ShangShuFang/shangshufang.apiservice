package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class StudentCollectionDTO extends BaseDTO {
    private int collectionID;
    private int studentID;
    private int companyID;
}
