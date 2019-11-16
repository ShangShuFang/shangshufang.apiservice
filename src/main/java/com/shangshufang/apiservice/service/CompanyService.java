package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyService extends BaseService<CompanyDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int provinceCode, int cityCode);

    UnifiedResponse find(int companyID);

    UnifiedResponse checkCompanyNameExist(String companyName);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse changeBrand(CompanyDTO dto);

    UnifiedResponse delete(int companyID);
}
