package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.StudentCollectionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface StudentCollectionService {
    UnifiedResponse findList(int pageNumber, int pageSize, int studentID);

    UnifiedResponse checkCollected(int studentID, int companyID);

    UnifiedResponse add(StudentCollectionDTO dto);

    UnifiedResponse delete(int studentID, int companyID);
}
