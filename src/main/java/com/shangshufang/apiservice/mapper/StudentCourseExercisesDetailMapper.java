package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCourseExercisesDetailEntity;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCourseExercisesDetailMapper extends BaseMapper<StudentCourseExercisesDetailEntity> {
    int searchKnowledgeCorrectingTotalCount(@Param("courseExercisesID") int courseExercisesID,
                                            @Param("technologyID") int technologyID,
                                            @Param("knowledgeID") int knowledgeID);

    int searchKnowledgeIncorrectTotalCount(@Param("courseExercisesID") int courseExercisesID,
                                           @Param("technologyID") int technologyID,
                                           @Param("knowledgeID") int knowledgeID);

    int searchLearnedKnowledgeTotalCount(@Param("studentID") int studentID, @Param("technologyID") int technologyID);

    int searchWeaknessKnowledgeTotalCount(@Param("studentID") int studentID, @Param("technologyID") int technologyID);

    List<TechnologyKnowledgeEntity> searchStudentLearnedKnowledgeList(@Param("startIndex") int startIndex,
                                                                      @Param("pageSize") int pageSize,
                                                                      @Param("studentID") int studentID,
                                                                      @Param("technologyID") int technologyID);

    List<TechnologyKnowledgeEntity> searchStudentWeaknessKnowledgeList(@Param("startIndex") int startIndex,
                                                                       @Param("pageSize") int pageSize,
                                                                       @Param("studentID") int studentID,
                                                                       @Param("technologyID") int technologyID);

    List<TechnologyKnowledgeEntity> searchStudentNoLearnKnowledgeList(@Param("startIndex") int startIndex,
                                                                      @Param("pageSize") int pageSize,
                                                                      @Param("studentID") int studentID,
                                                                      @Param("technologyID") int technologyID);

    List<StudentCourseExercisesDetailEntity> searchChoiceList(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesDetailEntity> searchBlankList(@Param("courseExercisesID") int courseExercisesID);

    List<StudentCourseExercisesDetailEntity> searchProgramList(@Param("courseExercisesID") int courseExercisesID);
}
