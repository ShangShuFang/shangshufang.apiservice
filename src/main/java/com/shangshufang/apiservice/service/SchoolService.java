package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.SchoolDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface SchoolService extends BaseService<SchoolDTO> {
    UnifiedResponse findList(int startIndex, int pageSize, int universityCode, String dataStatus);

    UnifiedResponse find(int universityCode, int schoolID);

    UnifiedResponse checkSchoolNameExist(int universityCode, String schoolName);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse delete(int universityCode, int schoolID);
}
