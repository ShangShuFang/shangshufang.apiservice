package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university/student/exercises")
public class UniversityStudentExercisesController {
    @Autowired
    private UniversityStudentExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{courseUniversityCode}/{courseSchoolID}/{courseID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("courseUniversityCode") int courseUniversityCode,
                                    @PathVariable("courseSchoolID") int courseSchoolID,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findList(pageNumber, pageSize, courseUniversityCode, courseSchoolID, courseID, dataStatus);
    }

    @RequestMapping(value="/assign", method = RequestMethod.POST)
    public UnifiedResponse assign(@RequestBody UniversityStudentExercisesDTO dto){
        return serviceImpl.assign(dto);
    }
}
