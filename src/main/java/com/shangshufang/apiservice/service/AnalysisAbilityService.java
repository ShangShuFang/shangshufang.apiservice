package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface AnalysisAbilityService {
    UnifiedResponse analyse();

    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int directionID,
                             int categoryID,
                             int technologyID,
                             int universityCode,
                             int schoolID,
                             int studentID,
                             String studentName);

    UnifiedResponse findStudentAbilitySummary(int studentID);

    UnifiedResponse findLearningTechnologyList(int studentID);

    UnifiedResponse findTechnologySummary(int studentID, int technologyID);

    UnifiedResponse findCodeGuidelineSummary(int studentID, int languageID);

    UnifiedResponse findFinishedKnowledgeList(int pageNumber,
                                              int pageSize,
                                              int studentID,
                                              int technologyID);

    UnifiedResponse findWeaknessKnowledgeList(int pageNumber,
                                              int pageSize,
                                              int studentID,
                                              int technologyID);

    UnifiedResponse findNoLearningKnowledgeList(int pageNumber,
                                                int pageSize,
                                                int studentID,
                                                int technologyID);

    UnifiedResponse selectComprehensiveExercisesAnalysisList(int studentID);

    UnifiedResponse selectComprehensiveExercisesSubmitList(int studentID);

    UnifiedResponse searchComprehensiveExercisesKnowledgeAnalysis(int studentID, int technologyID);
}
