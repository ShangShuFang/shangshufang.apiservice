package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ExercisesFileDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ExercisesFileService {
    UnifiedResponse findList(int exercisesID);

    UnifiedResponse add(ExercisesFileDTO dto);
}
