package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyAccountDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyAccountService extends BaseService<CompanyAccountDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int companyID);

    UnifiedResponse login(String cellphone, String password);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse delete(int companyID, int customerID, int accountID);
}
