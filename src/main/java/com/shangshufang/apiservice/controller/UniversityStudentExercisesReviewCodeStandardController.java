package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesReviewCodeStandardServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/codeStandard")
public class UniversityStudentExercisesReviewCodeStandardController {
    @Autowired
    private UniversityStudentExercisesReviewCodeStandardServiceImpl serviceImpl;

    @RequestMapping(value = "/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}/{reviewID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("reviewID") int reviewID){
        return serviceImpl.findList(studentUniversityCode, studentSchoolID, studentID, technologyID, reviewID);
    }
}
