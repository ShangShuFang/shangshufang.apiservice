package com.shangshufang.apiservice.entity;

import lombok.Data;

@Data
class BaseEntity {
    private String dataStatus;
    private String dataStatusText;
    private String createUser;
    private String createTime;
    private String updateUser;
    private String updateTime;
}
