package com.shangshufang.apiservice.dto;

import lombok.Data;

@Data
public class TechnologyCodeStandardDTO extends BaseDTO {
    private int codeStandardID;
    private int technologyID;
    private String codeStandardName;
}
