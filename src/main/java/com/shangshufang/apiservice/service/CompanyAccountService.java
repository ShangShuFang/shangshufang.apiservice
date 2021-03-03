package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyAccountDTO;
import com.shangshufang.apiservice.dto.CompanyCustomerDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CompanyAccountService extends BaseService<CompanyCustomerDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int companyID);

    UnifiedResponse login(String cellphone, String password);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse changePassword(CompanyCustomerDTO dto);

    UnifiedResponse delete(int companyID, int customerID, int accountID);
}
