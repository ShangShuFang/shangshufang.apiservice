package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyTalentPoolDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyTalentPoolService extends BaseService<CompanyTalentPoolDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int companyID, String dataStatus);

    UnifiedResponse find(int companyID, int studentID);
}
