package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesDTO;
import com.shangshufang.apiservice.service.impl.StudentComprehensiveExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/exercises/comprehensive")
public class StudentComprehensiveExercisesController {
    @Autowired
    private StudentComprehensiveExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{studentID}/{directionID}/{categoryID}/{technologyID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("directionID") int directionID,
                                    @PathVariable("categoryID") int categoryID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, studentID, directionID, categoryID, technologyID, dataStatus);
    }

    @RequestMapping(value = "/check/collected/{studentID}/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse checkCollected(@PathVariable("studentID") int studentID,
                                          @PathVariable("exercisesID") int exercisesID) {
        return serviceImpl.checkCollected(studentID, exercisesID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody StudentComprehensiveExercisesDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody StudentComprehensiveExercisesDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/delete/{studentID}/{exercisesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("studentID") int studentID, @PathVariable("exercisesID") int exercisesID){
        return serviceImpl.delete(studentID, exercisesID);
    }
}
