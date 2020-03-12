package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class StudentAbilityAnalysisVO {
    private UniversityStudentVO studentVO;
    private int readingPlace;
    private int onlineQuestionPlace;
    private int onlineAnswerPlace;
    List<StudentTechnologyDetailAnalysisVO> technologyDetailAnalysisList;
}
