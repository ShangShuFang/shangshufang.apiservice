package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentComprehensiveExercisesEntity;
import com.shangshufang.apiservice.entity.TechnologyEntity;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentComprehensiveExercisesMapper extends BaseMapper<StudentComprehensiveExercisesEntity> {
    int searchTotalCount(@Param("programLanguage") int programLanguage,
                         @Param("universityCode") int universityCode,
                         @Param("schoolID") int schoolID,
                         @Param("majorID") int majorID,
                         @Param("fullName") String fullName,
                         @Param("dataStatus") String dataStatus);

    List<StudentComprehensiveExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                         @Param("pageSize") int pageSize,
                                                         @Param("programLanguage") int programLanguage,
                                                         @Param("universityCode") int universityCode,
                                                         @Param("schoolID") int schoolID,
                                                         @Param("majorID") int majorID,
                                                         @Param("fullName") String fullName,
                                                         @Param("dataStatus") String dataStatus);

    int searchTotalCount4Student(@Param("studentID") int studentID,
                                 @Param("directionCode") int directionCode,
                                 @Param("programLanguage") int programLanguage,
                                 @Param("difficultyLevelCode") int difficultyLevelCode,
                                 @Param("dataStatus") String dataStatus);

    int searchTotalCountWithTechnology(@Param("studentID") int studentID, @Param("technologyID") int technologyID);

    List<StudentComprehensiveExercisesEntity> searchList4Student(@Param("startIndex") int startIndex,
                                                                 @Param("pageSize") int pageSize,
                                                                 @Param("studentID") int studentID,
                                                                 @Param("directionCode") int directionCode,
                                                                 @Param("programLanguage") int programLanguage,
                                                                 @Param("difficultyLevelCode") int difficultyLevelCode,
                                                                 @Param("dataStatus") String dataStatus);


    StudentComprehensiveExercisesEntity search(@Param("studentID") int studentID,
                                               @Param("exercisesID") int exercisesID);

    List<TechnologyEntity> searchTechnologyList(@Param("studentID") int studentID);

    int searchLearnedKnowledgeTotalCount(@Param("studentID") int studentID,
                                         @Param("technologyID") int technologyID);

    List<StudentComprehensiveExercisesEntity> searchComprehensiveExercisesWithTechnology(@Param("studentID") int studentID,
                                                                                         @Param("technologyID") int technologyID);

    List<TechnologyKnowledgeEntity> searchLearnedKnowledgeList(@Param("studentID") int studentID,
                                                               @Param("technologyID") int technologyID);

    int checkCollected(@Param("studentID") int studentID, @Param("exercisesID") int exercisesID);

    int delete(@Param("studentID") int studentID, @Param("exercisesID") int exercisesID);
}
