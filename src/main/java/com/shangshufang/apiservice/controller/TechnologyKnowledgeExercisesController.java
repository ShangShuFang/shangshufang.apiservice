package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeExercisesDTO;
import com.shangshufang.apiservice.service.impl.TechnologyKnowledgeExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/knowledge/exercises")
public class TechnologyKnowledgeExercisesController {
    @Autowired
    private TechnologyKnowledgeExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{technologyID}/{learningPhaseID}/{knowledgeID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("learningPhaseID") int learningPhaseID,
                                    @PathVariable("knowledgeID") int knowledgeID){
        return serviceImpl.findList(pageNumber, pageSize, technologyID, learningPhaseID, knowledgeID);
    }

    @RequestMapping(value = "/list/assigned/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findCourseAssignList(@PathVariable("universityCode") int universityCode,
                                                   @PathVariable("schoolID") int schoolID,
                                                   @PathVariable("courseID") int courseID){
        return serviceImpl.findCourseAssignList(universityCode, schoolID, courseID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyKnowledgeExercisesDTO dto){
        return serviceImpl.add(dto);
    }
}
