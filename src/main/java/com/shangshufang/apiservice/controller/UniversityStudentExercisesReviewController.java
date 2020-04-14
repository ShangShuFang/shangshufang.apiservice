package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewDTO;
import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesReviewCodeStandardServiceImpl;
import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesReviewServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/exercises/review")
public class UniversityStudentExercisesReviewController {
    @Autowired
    private UniversityStudentExercisesReviewServiceImpl serviceImpl;

    @Autowired
    private UniversityStudentExercisesReviewCodeStandardServiceImpl codeStandardServiceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("exercisesID") int exercisesID) {
        return serviceImpl.findList(pageNumber, pageSize, exercisesID);
    }

    @RequestMapping(value = "/list/code_guideline/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}/{reviewID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("reviewID") int reviewID){
        return codeStandardServiceImpl.findList(studentUniversityCode, studentSchoolID, studentID, technologyID, reviewID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityStudentExercisesReviewDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityStudentExercisesReviewDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

}
