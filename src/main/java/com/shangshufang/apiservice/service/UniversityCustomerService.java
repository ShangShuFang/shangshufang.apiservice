package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityCustomerDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityCustomerService extends BaseService<UniversityCustomerDTO> {
    UnifiedResponse findList(int universityCode, int schoolID, String fullName);

    UnifiedResponse find(int customerID, String cellphone);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse checkEmailExist(String email);

    UnifiedResponse delete(int universityCode, int schoolID, int customerID);
}
