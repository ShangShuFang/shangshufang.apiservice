package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCodeStandardAnalysisEntity;
import com.shangshufang.apiservice.entity.UniversityStudentAbilityAnalysisEntity;
import com.shangshufang.apiservice.entity.UniversityStudentAbilityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UniversityStudentAbilityAnalysisMapper {

    List<UniversityStudentAbilityAnalysisEntity> searchDataSource4Base (int startIndex, int pageSize);

    int searchDataSource4KnowledgeCount (@Param("technologyID") int technologyID);

    int searchDataSource4FinishKnowledgeCount (int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    int searchDataSource4FinishExercisesCount (int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    float searchDataSource4OnceCompilationSuccessRate (int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    float searchDataSource4OnceRunSuccessRate (int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    int searchStudentTotalCountWithTechnology (@Param("technologyID") int technologyID);

    int searchStudentPositionNumberWithTechnology (int technologyID, double standardScore);

    List<UniversityStudentAbilityAnalysisEntity> searchDataSource4Analysis (int startIndex, int pageSize);



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

    int searchAbilityAnalysisTotalCount(int studentUniversityCode,
                         int studentSchoolID,
                         int studentID,
                         int technologyID);

    UniversityStudentAbilityEntity searchStudentAbilityInfo(int universityCode, int schoolID, int studentID);

    UniversityStudentAbilityAnalysisEntity searchStudentTechnologyAbility(int universityCode, int schoolID, int studentID, int technologyID);

    List<StudentCodeStandardAnalysisEntity> searchCodeStandardAnalysis(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    int insert(UniversityStudentAbilityAnalysisEntity entity);

    int update(UniversityStudentAbilityAnalysisEntity entity);

    int updatePositionSite(UniversityStudentAbilityAnalysisEntity entity);
}
