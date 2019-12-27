package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityAccountDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityAccountService extends BaseService<UniversityAccountDTO>  {
    UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID);

    UnifiedResponse login(String cellphone, String password, String accountRole);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse checkCellphone4ChangePassword(String cellphone);

    UnifiedResponse checkEmailExist(String email);

    UnifiedResponse changePassword(UniversityAccountDTO dto);

    UnifiedResponse delete(int universityCode, int schoolID, int accountID, int customerID);
}
