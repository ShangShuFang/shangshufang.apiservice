package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesReviewDTO;
import com.shangshufang.apiservice.service.impl.StudentComprehensiveExercisesReviewServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/exercises/comprehensive/review")
public class StudentComprehensiveExercisesReviewController {
    @Autowired
    private StudentComprehensiveExercisesReviewServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{studentID}/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("exercisesID") int exercisesID){
        return serviceImpl.findList(pageNumber, pageSize, studentID, exercisesID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody StudentComprehensiveExercisesReviewDTO dto){
        return serviceImpl.add(dto);
    }
}
