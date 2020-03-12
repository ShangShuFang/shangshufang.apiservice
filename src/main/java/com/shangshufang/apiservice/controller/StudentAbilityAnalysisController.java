package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.StudentTechnologyDetailAnalysisServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/ability/analysis")
public class StudentAbilityAnalysisController {
    @Autowired
    private StudentTechnologyDetailAnalysisServiceImpl serviceImpl;

    @RequestMapping(value = "/knowledge/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeAnalysisInfo(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("technologyID") int technologyID){
        return serviceImpl.findKnowledgeAnalysisInfo(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }

    @RequestMapping(value = "/exercise/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findExerciseAnalysisResult(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                     @PathVariable("studentSchoolID") int studentSchoolID,
                                                     @PathVariable("studentID") int studentID,
                                                     @PathVariable("technologyID") int technologyID){
        return serviceImpl.findExerciseAnalysisResult(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }

    @RequestMapping(value = "/exercisePercent/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findExercisePercentAnalysisResult(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                     @PathVariable("studentSchoolID") int studentSchoolID,
                                                     @PathVariable("studentID") int studentID,
                                                     @PathVariable("technologyID") int technologyID){
        return serviceImpl.findExercisePercentAnalysisResult(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }
}
