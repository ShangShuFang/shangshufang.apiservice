package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyService extends BaseService<TechnologyDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus);

    UnifiedResponse findList4Client(int pageNumber, int pageSize);

    UnifiedResponse findSimpleList();

    UnifiedResponse find(int technologyID);

    UnifiedResponse findStudentLearning(int studentUniversityCode, int studentSchoolID, int studentID);

    UnifiedResponse checkTechnologyNameExist(String technologyName);

    UnifiedResponse changeThumbnail(TechnologyDTO dto);

    UnifiedResponse delete(int technologyID);
}
