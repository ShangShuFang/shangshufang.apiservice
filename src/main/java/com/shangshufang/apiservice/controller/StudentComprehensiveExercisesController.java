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

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{programLanguage}/{universityCode}/{schoolID}/{majorID}/{fullName}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("programLanguage") int programLanguage,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("majorID") int majorID,
                                    @PathVariable("fullName") String fullName,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, programLanguage, universityCode, schoolID, majorID, fullName, dataStatus);
    }

    @RequestMapping(value = "/list/choose/{pageNumber}/{pageSize}/{studentID}/{directionCode}/{programLanguage}/{difficultyLevelCode}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList4Student(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("directionCode") int directionCode,
                                    @PathVariable("programLanguage") int programLanguage,
                                    @PathVariable("difficultyLevelCode") int difficultyLevelCode,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList4Student(pageNumber, pageSize, studentID, directionCode, programLanguage, difficultyLevelCode, dataStatus);
    }

    @RequestMapping(value = "/any/{studentID}/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("studentID") int studentID,
                                    @PathVariable("exercisesID") int exercisesID){
        return serviceImpl.find(studentID, exercisesID);
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

    @RequestMapping(value = "/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody StudentComprehensiveExercisesDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{studentID}/{exercisesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("studentID") int studentID, @PathVariable("exercisesID") int exercisesID){
        return serviceImpl.delete(studentID, exercisesID);
    }
}
