package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ResumeBrowsingHistoryDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ResumeBrowsingHistoryService extends BaseService<ResumeBrowsingHistoryDTO> {
    UnifiedResponse findBrowseStudentTotalCount (int companyID);

    UnifiedResponse findBrowsedByCompanyTotalCount (int studentID);

    UnifiedResponse findBrowseStudentList(int pageNumber, int pageSize, int companyID);

    UnifiedResponse findBrowsedByCompanyList(int pageNumber, int pageSize, int studentID);
}
