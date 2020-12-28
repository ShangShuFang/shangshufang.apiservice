package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ComprehensiveExercisesDTO;
import com.shangshufang.apiservice.service.impl.ComprehensiveExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercises/comprehensive")
public class ComprehensiveExercisesController {
    @Autowired
    private ComprehensiveExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{examType}/{difficultyLevel}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("examType") int examType,
                                    @PathVariable("difficultyLevel") int difficultyLevel,
                                    @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findList(pageNumber, pageSize, examType, difficultyLevel, dataStatus);
    }

    @RequestMapping(value = "/any/{exercisesID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("exercisesID") int exercisesID,
                                @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.find(exercisesID, dataStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ComprehensiveExercisesDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ComprehensiveExercisesDTO dto) {
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody ComprehensiveExercisesDTO dto) {
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{exercisesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("exercisesID") int exercisesID) {
        return serviceImpl.delete(exercisesID);
    }
}
