package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentComprehensiveExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentComprehensiveExercisesMapper extends BaseMapper<StudentComprehensiveExercisesEntity> {
    int searchTotalCount(@Param("studentID") int studentID,
                         @Param("directionID") int directionID,
                         @Param("categoryID") int categoryID,
                         @Param("technologyID") int technologyID,
                         @Param("dataStatus") String dataStatus);

    List<StudentComprehensiveExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                         @Param("pageSize") int pageSize,
                                                         @Param("studentID") int studentID,
                                                         @Param("directionID") int directionID,
                                                         @Param("categoryID") int categoryID,
                                                         @Param("technologyID") int technologyID,
                                                         @Param("dataStatus") String dataStatus);

    int checkCollected(@Param("studentID") int studentID, @Param("exercisesID") int exercisesID);

    int delete(@Param("studentID") int studentID, @Param("exercisesID") int exercisesID);
}
