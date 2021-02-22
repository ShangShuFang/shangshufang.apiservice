package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.AnalysisAbilityServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analysis/ability/student")
public class AnalysisAbilityController {
    @Autowired
    private AnalysisAbilityServiceImpl serviceImpl;

    @RequestMapping(value = "/analyse", method = RequestMethod.POST)
    public UnifiedResponse analyse() {
        return serviceImpl.analyse();
    }

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{directionID}/{categoryID}/{technologyID}/{universityCode}/{schoolID}/{studentName}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("directionID") int directionID,
                                    @PathVariable("categoryID") int categoryID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("studentName") String studentName,
                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findList(pageNumber, pageSize, directionID, categoryID, technologyID, universityCode, schoolID, studentID, studentName);
    }

    @RequestMapping(value = "/summary/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentAbilitySummary(@PathVariable("studentID") int studentID) {
        return serviceImpl.findStudentAbilitySummary(studentID);
    }

    @RequestMapping(value = "/list/technology/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningTechnologyList(@PathVariable("studentID") int studentID) {
        return serviceImpl.findLearningTechnologyList(studentID);
    }

    @RequestMapping(value = "/any/technology/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findTechnologySummary(@PathVariable("studentID") int studentID, @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findTechnologySummary(studentID, technologyID);
    }

    @RequestMapping(value = "/any/guideline/{studentID}/{languageID}", method = RequestMethod.GET)
    public UnifiedResponse findCodeGuidelineSummary(@PathVariable("studentID") int studentID, @PathVariable("languageID") int languageID) {
        return serviceImpl.findCodeGuidelineSummary(studentID, languageID);
    }

    @RequestMapping(value = "/list/knowledge/learned/{pageNumber}/{pageSize}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findFinishedKnowledgeList(@PathVariable("pageNumber") int pageNumber,
                                                   @PathVariable("pageSize") int pageSize,
                                                   @PathVariable("studentID") int studentID,
                                                   @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findFinishedKnowledgeList(pageNumber, pageSize, studentID, technologyID);
    }

    @RequestMapping(value = "/list/knowledge/weakness/{pageNumber}/{pageSize}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findWeaknessKnowledgeList(@PathVariable("pageNumber") int pageNumber,
                                                     @PathVariable("pageSize") int pageSize,
                                                     @PathVariable("studentID") int studentID,
                                                     @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findWeaknessKnowledgeList(pageNumber, pageSize, studentID, technologyID);
    }

    @RequestMapping(value = "/list/knowledge/nolearn/{pageNumber}/{pageSize}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findNoLearningKnowledgeList(@PathVariable("pageNumber") int pageNumber,
                                                       @PathVariable("pageSize") int pageSize,
                                                       @PathVariable("studentID") int studentID,
                                                       @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findNoLearningKnowledgeList(pageNumber, pageSize, studentID, technologyID);
    }

    @RequestMapping(value = "/list/comprehensive/analysis/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse selectComprehensiveExercisesAnalysisList(@PathVariable("studentID") int studentID) {
        return serviceImpl.selectComprehensiveExercisesAnalysisList(studentID);
    }

    @RequestMapping(value = "/list/comprehensive/submit/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse selectComprehensiveExercisesSubmitList(@PathVariable("studentID") int studentID) {
        return serviceImpl.selectComprehensiveExercisesSubmitList(studentID);
    }
}
