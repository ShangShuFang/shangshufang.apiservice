package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentExercisesReviewCodeStandardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityStudentExercisesReviewCodeStandardMapper extends BaseMapper<UniversityStudentExercisesReviewCodeStandardEntity> {
    List<UniversityStudentExercisesReviewCodeStandardEntity> searchList(int reviewID);
}
