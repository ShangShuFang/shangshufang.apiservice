package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.ExercisesKnowledgeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercises/knowledge")
public class ExercisesKnowledgeController {
    @Autowired
    private ExercisesKnowledgeServiceImpl serviceImpl;

    @RequestMapping(value = "/{exercisesID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("exercisesID") int exercisesID){
        return serviceImpl.findList(exercisesID);
    }
}
