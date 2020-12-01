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
    List<StudentAbilityAnalysisEntity> searchDataSource4Base(int startIndex, int pageSize);

    /**
     * 取得当前技术的知识点总数量
     *
     * @param technologyID 技术编号
     * @return 当前技术的知识点总数量
     */
    int searchDataSource4KnowledgeCount(@Param("technologyID") int technologyID);

    /**
     * 取得当前学生的能力分析结果在数据库中是否已存在
     * 学生所在二级学院编号
     *
     * @param studentID    学生编号
     * @param technologyID 技术编号
     * @return 当前学生的能力分析结果在数据库中是否已存在
     */
    int searchAbilityAnalysisTotalCount(@Param("studentID") int studentID,
                                        @Param("technologyID") int technologyID);

    /**
     * 将当前学生的能力分析结果保存到数据库
     *
     * @param entity 能力分析结果
     * @return 数据库影响行数
     */
    int insert(StudentAbilityAnalysisEntity entity);

    /**
     * 将当前学生的能力分析结果更新到数据库
     *
     * @param entity 能力分析结果
     * @return 数据库影响行数
     */
    int update(StudentAbilityAnalysisEntity entity);

    /**
     * 获取学生某个技术当前的能力该要
     *
     * @param studentID    开始下标
     * @param technologyID 查询数量
     * @return 学生某个技术当前的能力该要
     */
    StudentAbilityAnalysisEntity searchStudentAbility(@Param("studentID") int studentID, @Param("technologyID") int technologyID);

    /**
     * 取得学习当前技术的学生总数
     *
     * @param technologyID 技术编号
     * @return 学习当前技术的学生总数
     */
    int searchStudentTotalCountWithTechnology(@Param("technologyID") int technologyID);

    /**
     * 更新学生技术能力名次的站位信息
     *
     * @param entity 学生能力数据
     * @return 数据库影响行数
     */
    int updatePositionSite(StudentAbilityAnalysisEntity entity);

    //endregion

    //region 学生能力分析概要信息

    /**
     * 取得满足条件的学生能力结果的总数
     *
     * @param directionID  方向编号
     * @param categoryID   分类编号
     * @param technologyID 技术编号
     * @param studentName  学生姓名
     * @param studentID    学生编号（需排除）
     * @return 满足条件的学生能力结果的总数
     */
    int searchStudentAbilityTotalCount(@Param("directionID") int directionID,
                                       @Param("categoryID") int categoryID,
                                       @Param("technologyID") int technologyID,
                                       @Param("universityCode") int universityCode,
                                       @Param("schoolID") int schoolID,
                                       @Param("studentID") int studentID,
                                       @Param("studentName") String studentName);

    /**
     * 取得满足条件的学生能力结果列表
     *
     * @param startIndex   开始下标
     * @param pageSize     查询数量
     * @param directionID  方向编号
     * @param categoryID   分类编号
     * @param technologyID 技术编号
     * @param studentID    学生编号（需排除）
     * @param studentName  学生姓名
     * @return 满足条件的学生能力结果列表
     */
    List<StudentAbilityAnalysisEntity> searchStudentAbilityList(@Param("startIndex") int startIndex,
                                                                @Param("pageSize") int pageSize,
                                                                @Param("directionID") int directionID,
                                                                @Param("categoryID") int categoryID,
                                                                @Param("technologyID") int technologyID,
                                                                @Param("universityCode") int universityCode,
                                                                @Param("schoolID") int schoolID,
                                                                @Param("studentID") int studentID,
                                                                @Param("studentName") String studentName);
    //endregion

    //region 学生能力分析详细信息

    /**
     * 取得学生概要信息
     *
     * @param studentID 学生编号
     * @return 学生概要信息
     */
    StudentAbilitySummaryEntity searchStudentSummary(@Param("studentID") int studentID);

    /**
     * 取得学生所学的所有技术
     *
     * @param studentID 学生编号
     * @return 学生所学的所有技术
     */
    List<StudentAbilityAnalysisEntity> searchLearningTechnologyList(@Param("studentID") int studentID);


    //endregion

    List<CodeGuidelineSummaryEntity> searchCodeGuidelineSummaryList(@Param("studentID") int studentID, @Param("languageID") int languageID);

    /**
     * 取得指定技术，能力最强的前几名的学生
     *
     * @param technologyID       技术
     * @param topNum             取得人数
     * @return 指定技术，能力最强的前几名的学生
     */
    List<StudentAbilityAnalysisEntity> searchTopStudentSummary(@Param("technologyID") int technologyID, @Param("topNum") int topNum);


    List<StudentAbilityAnalysisEntity> searchTechnologyList();

    int searchStudentTotalCount(@Param("technologyID") int technologyID);

    int searchLowerThanScoreTotalCount(@Param("technologyID") int technologyID,
                                       @Param("standardScore") double standardScore);

    List<StudentAbilityAnalysisEntity> searchStudentList(@Param("technologyID") int technologyID,
                                                         @Param("startIndex") int startIndex,
                                                         @Param("pageSize") int pageSize);
}
