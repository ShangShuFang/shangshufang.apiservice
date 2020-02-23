package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.DirectionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface DirectionService extends BaseService<DirectionDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus);

    UnifiedResponse find(int directionID);

    UnifiedResponse checkDirectionNameExist(String directionName);

    UnifiedResponse changeThumbnail(DirectionDTO dto);

    UnifiedResponse delete(int directionID);
}
