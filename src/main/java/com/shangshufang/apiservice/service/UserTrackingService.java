package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UserTrackingDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UserTrackingService{
    UnifiedResponse add(UserTrackingDTO dto);
}
