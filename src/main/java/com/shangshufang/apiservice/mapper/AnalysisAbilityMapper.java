package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnalysisAbilityMapper {

    //region 学生能力批量分析

    /**
     * 获取待分析的基础数据
     *
     * @param startIndex 开始下标
     * @param pageSize   查询数量
     * @return 待分析的基础数据
     */
    List<AbilityAnalysisResult4StudentMainInfoEntity> searchDataSource4Base(int startIndex, int pageSize);

    /**
     * 取得当前技术的知识点总数量
     *
     * @param technologyID 技术编号
     * @return 当前技术的知识点总数量
     */
    int searchDataSource4KnowledgeCount(@Param("technologyID") int technologyID);

    /**
     * 取得当前学生指定技术已掌握的知识点数量
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 当前学生指定技术已掌握的知识点数量
     */
    int searchDataSource4FinishKnowledgeCount(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    /**
     * 取得当前学生指定技术已完成的习题数量
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 当前学生指定技术已完成的习题数量
     */
    int searchDataSource4FinishExercisesCount(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    /**
     * 取得当前学生指定技术一次性编译通过率
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 当前学生指定技术一次性编译通过率
     */
    float searchDataSource4OnceCompilationSuccessRate(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    /**
     * 取得当前学生指定技术一次性运行成功率
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 当前学生指定技术一次性运行成功率
     */
    float searchDataSource4OnceRunSuccessRate(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    /**
     * 取得当前学生的能力分析结果在数据库中是否已存在
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 当前学生的能力分析结果在数据库中是否已存在
     */
    int searchAbilityAnalysisTotalCount(int studentUniversityCode,
                                        int studentSchoolID,
                                        int studentID,
                                        int technologyID);

    /**
     * 将当前学生的能力分析结果保存到数据库
     *
     * @param entity 能力分析结果
     * @return 数据库影响行数
     */
    int insert(AbilityAnalysisResult4StudentMainInfoEntity entity);

    /**
     * 将当前学生的能力分析结果更新到数据库
     *
     * @param entity 能力分析结果
     * @return 数据库影响行数
     */
    int update(AbilityAnalysisResult4StudentMainInfoEntity entity);

    /**
     * 获取待分析名次的原数据
     *
     * @param startIndex 开始下标
     * @param pageSize   查询数量
     * @return 获取待分析名次的原数据
     */
    List<AbilityAnalysisResult4StudentMainInfoEntity> searchDataSource4Analysis(int startIndex, int pageSize);

    /**
     * 取得学习当前技术的学生总数
     *
     * @param technologyID 技术编号
     * @return 学习当前技术的学生总数
     */
    int searchStudentTotalCountWithTechnology(@Param("technologyID") int technologyID);

    /**
     * 取得学习指定技术的学生中，低于指定标准分的学生总数
     *
     * @param technologyID  技术编号
     * @param standardScore 参考分析
     * @return 低于指定标准分的学生总数
     */
    int searchStudentPositionNumberWithTechnology(int technologyID, double standardScore);

    /**
     * 更新学生技术能力名次的站位信息
     *
     * @param entity 学生能力数据
     * @return 数据库影响行数
     */
    int updatePositionSite(AbilityAnalysisResult4StudentMainInfoEntity entity);

    //endregion

    //region 学生能力分析概要信息

    /**
     * 取得满足条件的学生能力结果的总数
     *
     * @param directionID    方向编号
     * @param categoryID     分类编号
     * @param technologyID   技术编号
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentName    学生姓名
     * @param studentID      学生编号（需排除）
     * @return 满足条件的学生能力结果的总数
     */
    int searchStudentMainResultTotalCount(@Param("directionID") int directionID,
                                          @Param("categoryID") int categoryID,
                                          @Param("technologyID") int technologyID,
                                          @Param("universityCode") int universityCode,
                                          @Param("schoolID") int schoolID,
                                          @Param("studentName") String studentName,
                                          @Param("studentID") int studentID);

    /**
     * 取得满足条件的学生能力结果列表
     *
     * @param startIndex            开始下标
     * @param pageSize              查询数量
     * @param directionID           方向编号
     * @param categoryID            分类编号
     * @param technologyID          技术编号
     * @param universityCode        学生所在高校
     * @param schoolID              学生所在二级学院
     * @param studentName           学生姓名
     * @param studentID             学生编号（需排除）
     * @return 满足条件的学生能力结果列表
     */
    List<AbilityAnalysisResult4StudentMainInfoEntity> searchStudentMainResultList(@Param("startIndex") int startIndex,
                                                                                  @Param("pageSize") int pageSize,
                                                                                  @Param("directionID") int directionID,
                                                                                  @Param("categoryID") int categoryID,
                                                                                  @Param("technologyID") int technologyID,
                                                                                  @Param("universityCode") int universityCode,
                                                                                  @Param("schoolID") int schoolID,
                                                                                  @Param("studentName") String studentName,
                                                                                  @Param("studentID") int studentID);
    //endregion

    //region 学生能力分析详细信息

    /**
     * 取得学生概要信息
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @return 学生概要信息
     */
    AbilityAnalysisResult4StudentSummaryEntity searchStudentSummaryResult(int universityCode, int schoolID, int studentID);

    /**
     * 取得学生所学的所有技术
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @return 学生所学的所有技术
     */
    List<AbilityAnalysisResult4LearningTechnologySummaryEntity> searchLearningTechnologyResultList(int universityCode, int schoolID, int studentID);

    /**
     * 取得学生指定技术代码规范性问题分析
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @param languageID     编程语言编号
     * @return 学生指定技术代码规范性问题分析
     */
    List<AbilityAnalysisResult4CodeGuidelineSummaryEntity> searchCodeStandardAnalysisResultList(int universityCode, int schoolID, int studentID, int languageID);

    /**
     * 取得学生已学习的知识点总数
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @param technologyID   技术编号
     * @return 学生已学习的知识点总数
     */
    int searchLearningKnowledgeTotalCount(int universityCode, int schoolID, int studentID, int technologyID);

    /**
     * 取得学生练习题完成数量趋势
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @param technologyID   技术编号
     * @return 学生练习题完成数量趋势
     */
    List<AbilityAnalysisResult4ExerciseNumberTrendEntity> searchExerciseNumberTrend(int universityCode, int schoolID, int studentID, int technologyID);

    /**
     * 取得学生练习题完成百分比趋势
     *
     * @param universityCode 学生所在高校
     * @param schoolID       学生所在二级学院
     * @param studentID      学生编号
     * @param technologyID   技术编号
     * @return 学生练习题完成百分比趋势
     */
    List<AbilityAnalysisResult4ExercisePercentTrendEntity> searchExercisePercentTrend(int universityCode, int schoolID, int studentID, int technologyID);
    //endregion

    /**
     * 取得指定技术，能力最强的前几名的学生
     *
     * @param technologyID       技术
     * @param lowestRecruitLevel 最低级别
     * @param topNum             取得人数
     * @return 指定技术，能力最强的前几名的学生
     */
    List<AbilityAnalysisResult4StudentMainInfoEntity> searchTopStudentSummary(int technologyID, String lowestRecruitLevel, int topNum);

    /**
     * 取得学生已经掌握的知识点列表
     * @param startIndex 高校编码
     * @param pageSize 高校编码
     * @param universityCode 高校编码
     * @param schoolID 二级学院编号
     * @param studentID 学生编号
     * @param technologyID 技术编号
     * @return 已掌握的知识点列表
     */
    List<TechnologyKnowledgeEntity> searchFinishKnowledgeList (@Param("startIndex") int startIndex,
                                                               @Param("pageSize") int pageSize,
                                                               @Param("universityCode") int universityCode,
                                                               @Param("schoolID") int schoolID,
                                                               @Param("studentID") int studentID,
                                                               @Param("technologyID") int technologyID);

    /**
     * 取得学生较薄弱的知识点列表
     * @param startIndex 高校编码
     * @param pageSize 高校编码
     * @param universityCode 高校编码
     * @param schoolID 二级学院编号
     * @param studentID 学生编号
     * @param technologyID 技术编号
     * @return 较薄弱的知识点列表
     */
    List<TechnologyKnowledgeEntity> searchWeaknessKnowledgeList (@Param("startIndex") int startIndex,
                                                                 @Param("pageSize") int pageSize,
                                                                 @Param("universityCode") int universityCode,
                                                                 @Param("schoolID") int schoolID,
                                                                 @Param("studentID") int studentID,
                                                                 @Param("technologyID") int technologyID);

    /**
     * 取得学生未掌握的知识点列表
     * @param startIndex 高校编码
     * @param pageSize 高校编码
     * @param universityCode 高校编码
     * @param schoolID 二级学院编号
     * @param studentID 学生编号
     * @param technologyID 技术编号
     * @return 未掌握的知识点列表
     */
    List<TechnologyKnowledgeEntity> searchNoLearningKnowledgeList (@Param("startIndex") int startIndex,
                                                                   @Param("pageSize") int pageSize,
                                                                   @Param("universityCode") int universityCode,
                                                                   @Param("schoolID") int schoolID,
                                                                   @Param("studentID") int studentID,
                                                                   @Param("technologyID") int technologyID);

}
