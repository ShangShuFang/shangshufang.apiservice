package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ExercisesDTO;
import com.shangshufang.apiservice.service.impl.ExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")
public class ExercisesController {
    @Autowired
    private ExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{exercisesType}/{technologyID}/{learningPhaseID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("exercisesType") String exercisesType,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("learningPhaseID") int learningPhaseID){
        return serviceImpl.findList(pageNumber, pageSize, exercisesType, technologyID, learningPhaseID);
    }

    @RequestMapping(value = "/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("exercisesID") int exercisesID){
        return serviceImpl.find(exercisesID);
    }

    @RequestMapping(value = "/checkExercisesCode/{exercisesCode}", method = RequestMethod.GET)
    public UnifiedResponse checkExercisesCodeExist(@PathVariable("exercisesCode") String exercisesCode){
        return serviceImpl.checkExercisesCodeExist(exercisesCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ExercisesDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ExercisesDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody ExercisesDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/{exercisesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("exercisesID") int exercisesID){
        return serviceImpl.delete(exercisesID);
    }
}
