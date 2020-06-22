package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ComprehensiveExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComprehensiveExercisesMapper extends BaseMapper<ComprehensiveExercisesEntity> {
    int searchTotalCount(@Param("directionID") int directionID,
                         @Param("categoryID") int categoryID,
                         @Param("technologyID") int technologyID,
                         @Param("dataStatus") String dataStatus);

    List<ComprehensiveExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                  @Param("pageSize") int pageSize,
                                                  @Param("directionID") int directionID,
                                                  @Param("categoryID") int categoryID,
                                                  @Param("technologyID") int technologyID,
                                                  @Param("dataStatus") String dataStatus);

    int delete(int exercisesID);
}
