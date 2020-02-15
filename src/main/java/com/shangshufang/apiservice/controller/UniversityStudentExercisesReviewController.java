package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewDTO;
import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesReviewServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university/student/exercises/review")
public class UniversityStudentExercisesReviewController {
    @Autowired
    private UniversityStudentExercisesReviewServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{studentExercisesID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentExercisesID") int studentExercisesID) {
        return serviceImpl.findList(pageNumber, pageSize, studentExercisesID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityStudentExercisesReviewDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityStudentExercisesReviewDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

}
