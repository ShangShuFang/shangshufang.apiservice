package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityStudentExercisesMapper extends BaseMapper<UniversityStudentExercisesEntity> {
//    int searchTotalCount4Technology(@Param("universityCode") int universityCode,
//                                    @Param("schoolID") int schoolID,
//                                    @Param("studentID") int studentID,
//                                    @Param("technologyID") int technologyID,
//                                    @Param("dataStatus") String dataStatus);
//
//    List<UniversityStudentExercisesEntity> searchList4Technology(@Param("startIndex") int startIndex,
//                                                                 @Param("pageSize") int pageSize,
//                                                                 @Param("universityCode") int universityCode,
//                                                                 @Param("schoolID") int schoolID,
//                                                                 @Param("studentID") int studentID,
//                                                                 @Param("technologyID") int technologyID,
//                                                                 @Param("dataStatus") String dataStatus);
}
