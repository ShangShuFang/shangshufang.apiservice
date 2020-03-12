package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class StudentTechnologyDetailAnalysisVO {
    private String rank;
    private int universityPlace;
    private int platformPlace;
    private int completePercent;
    private StudentKnowledgeAnalysisVO knowledgeAnalysisVO;
    private List<StudentCodeStandardAnalysisVO> codeStandardAnalysisList;
    private List<StudentExerciseAnalysisVO> exerciseAnalysisList;
    private List<StudentOnlineAnswerAnalysisVO> onlineAnswerAnalysisList;
    private List<StudentOnlineQuestionAnalysisVO> onlineQuestionAnalysisList;

}
