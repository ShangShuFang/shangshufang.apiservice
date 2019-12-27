package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.VerificationCodeDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface VerificationCodeService extends BaseService<VerificationCodeDTO> {
    UnifiedResponse find(String cellphone, String code);
}
