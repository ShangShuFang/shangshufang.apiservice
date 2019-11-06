package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityService extends BaseService<UniversityDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int provinceCode, int cityCode);

    UnifiedResponse find(int universityCode);

    UnifiedResponse checkUniversityCodeExist(String universityCode);

    UnifiedResponse checkUniversityNameExist(String universityName);

    UnifiedResponse delete(int universityID);
}
