package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityStudentDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentService extends BaseService<UniversityStudentDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID, String fullName);

    UnifiedResponse find(int universityCode, int schoolID, int studentID);

    UnifiedResponse checkCellphoneExist(String cellphone);

    UnifiedResponse checkEmailExist(String email);

    UnifiedResponse changePassword(UniversityStudentDTO dto);
}
