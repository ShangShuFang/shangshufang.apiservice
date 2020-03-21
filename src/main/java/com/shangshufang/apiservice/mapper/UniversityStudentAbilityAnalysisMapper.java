package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentAbilityAnalysisEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UniversityStudentAbilityAnalysisMapper {

    int searchTotalCount(int technologyID,
                         int studentUniversityCode,
                         int studentSchoolID,
                         int teacherUniversityCode,
                         int teacherSchoolID,
                         int teacherID,
                         String studentCellphone);

    List<UniversityStudentAbilityAnalysisEntity> searchStudentAbilityAnalysisList(int startIndex,
                                                                                  int pageSize,
                                                                                  int technologyID,
                                                                                  int studentUniversityCode,
                                                                                  int studentSchoolID,
                                                                                  int teacherUniversityCode,
                                                                                  int teacherSchoolID,
                                                                                  int teacherID,
                                                                                  String studentCellphone);

    List<UniversityStudentAbilityAnalysisEntity> searchAnalysisList(Map<String, Object> param);



    int searchAbilityAnalysisTotalCount(int studentUniversityCode,
                         int studentSchoolID,
                         int studentID,
                         int technologyID);

    int insert(UniversityStudentAbilityAnalysisEntity entity);

    int update(UniversityStudentAbilityAnalysisEntity entity);
}
