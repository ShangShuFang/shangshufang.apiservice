package com.shangshufang.apiservice.vo;

import lombok.Data;
import java.util.List;

@Data
public class ComprehensiveExercisesAnalysisVO {
    private int technologyID;
    private String technologyName;
    private int totalCount;
    private int submitTotalCount;
    private int correctTotalCount;
    private int errorTotalCount;
    private int unSubmitTotalCount;
    private List<ComprehensiveExercisesKnowledgeAnalysisVO> knowledgeAnalysisVOList;
}
