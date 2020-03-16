package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
public class TechnologyCodeStandardEntity extends BaseEntity {
    private int codeStandardID;
    private int technologyID;
    private String codeStandardName;
}
