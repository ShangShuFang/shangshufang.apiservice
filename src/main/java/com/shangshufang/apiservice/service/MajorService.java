package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.MajorDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface MajorService extends BaseService<MajorDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID);

    UnifiedResponse checkNameExist(int universityCode, int schoolID, String majorName);

    UnifiedResponse delete(int universityCode, int schoolID, int majorID);
}
