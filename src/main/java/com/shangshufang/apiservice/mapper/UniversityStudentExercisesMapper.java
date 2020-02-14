package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentExercisesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityStudentExercisesMapper extends BaseMapper<UniversityStudentExercisesEntity> {
    int searchTotalCount(int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus);

    List<UniversityStudentExercisesEntity> searchList(int startIndex, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus);
}
