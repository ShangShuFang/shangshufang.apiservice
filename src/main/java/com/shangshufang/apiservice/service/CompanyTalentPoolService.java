package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyTalentPoolDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyTalentPoolService extends BaseService<CompanyTalentPoolDTO> {
    UnifiedResponse findListWithCompany(int pageNumber, int pageSize, int companyID, int technologyID, String dataStatus);

    UnifiedResponse findListWithStudent(int pageNumber, int pageSize, int studentID, String dataStatus);

    UnifiedResponse find(int companyID, int studentID);
}
