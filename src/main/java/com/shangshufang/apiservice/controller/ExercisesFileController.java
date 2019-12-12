package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ExercisesFileDTO;
import com.shangshufang.apiservice.service.impl.ExercisesFileServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises/file")
public class ExercisesFileController {
    @Autowired
    private ExercisesFileServiceImpl serviceImpl;

    @RequestMapping(value = "/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("exercisesID") int exercisesID){
        return serviceImpl.findList(exercisesID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ExercisesFileDTO dto){
        return serviceImpl.add(dto);
    }
}
