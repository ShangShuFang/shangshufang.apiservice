package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyCollectionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyCollectionService {
    UnifiedResponse findList(int pageNumber, int pageSize, int studentID);

    UnifiedResponse checkCollected(int companyID, int studentID);

    UnifiedResponse add(CompanyCollectionDTO dto);

    UnifiedResponse delete(int companyID, int studentID);
}
