package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityStudentExercisesMapper extends BaseMapper<UniversityStudentExercisesEntity> {
    int searchTotalCount(@Param("courseUniversityCode") int courseUniversityCode,
                         @Param("courseSchoolID") int courseSchoolID,
                         @Param("courseID") int courseID,
                         @Param("dataStatus") String dataStatus,
                         @Param("studentName") String studentName);

    List<UniversityStudentExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                      @Param("pageSize") int pageSize,
                                                      @Param("courseUniversityCode") int courseUniversityCode,
                                                      @Param("courseSchoolID") int courseSchoolID,
                                                      @Param("courseID") int courseID,
                                                      @Param("dataStatus") String dataStatus,
                                                      @Param("studentName") String studentName);

    int searchTotalCount4Student(@Param("studentID") int studentID,
                                 @Param("courseID") int courseID,
                                 @Param("dataStatus") String dataStatus,
                                 @Param("studentName") String studentName,
                                 @Param("isSelf") boolean isSelf);

    List<UniversityStudentExercisesEntity> searchList4Student(@Param("startIndex") int startIndex,
                                                              @Param("pageSize") int pageSize,
                                                              @Param("studentID") int studentID,
                                                              @Param("courseID") int courseID,
                                                              @Param("dataStatus") String dataStatus,
                                                              @Param("studentName") String studentName,
                                                              @Param("isSelf") boolean isSelf);

    int searchTotalCount4Technology(@Param("universityCode") int universityCode,
                                    @Param("schoolID") int schoolID,
                                    @Param("studentID") int studentID,
                                    @Param("technologyID") int technologyID,
                                    @Param("dataStatus") String dataStatus);

    List<UniversityStudentExercisesEntity> searchList4Technology(@Param("startIndex") int startIndex,
                                                                 @Param("pageSize") int pageSize,
                                                                 @Param("universityCode") int universityCode,
                                                                 @Param("schoolID") int schoolID,
                                                                 @Param("studentID") int studentID,
                                                                 @Param("technologyID") int technologyID,
                                                                 @Param("dataStatus") String dataStatus);
}
