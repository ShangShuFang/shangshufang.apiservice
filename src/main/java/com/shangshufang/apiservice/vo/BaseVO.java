package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class BaseVO {
    private String dataStatus;
    private String dataStatusText;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}
