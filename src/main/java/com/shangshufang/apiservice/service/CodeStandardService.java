package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CodeStandardDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CodeStandardService extends BaseService<CodeStandardDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int languageID);

    UnifiedResponse checkNameExist(int languageID, String codeStandardName);

    UnifiedResponse delete(int languageID, int codeStandardID);
}
