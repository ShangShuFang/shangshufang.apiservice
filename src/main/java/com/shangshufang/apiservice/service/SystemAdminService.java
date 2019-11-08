package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface SystemAdminService {
    UnifiedResponse login(String cellphone, String password);
}
