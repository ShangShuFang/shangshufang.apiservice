package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentExercisesReviewEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityStudentExercisesReviewMapper extends BaseMapper<UniversityStudentExercisesReviewEntity> {
    int searchTotalCount(int studentExercisesID);

    List<UniversityStudentExercisesReviewEntity> searchList(int startIndex, int pageSize, int studentExercisesID);
}
