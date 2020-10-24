package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeBlankDTO;
import com.shangshufang.apiservice.service.impl.UniversityExerciseKnowledgeBlankServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/university/exercise/knowledge/blank")
public class UniversityExerciseKnowledgeBlankController {
    @Autowired
    private UniversityExerciseKnowledgeBlankServiceImpl myService;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{technologyID}/{knowledgeID}/{teacherID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("knowledgeID") int knowledgeID,
                                    @PathVariable("teacherID") int teacherID){
        return myService.findList(pageNumber, pageSize, technologyID, knowledgeID, teacherID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityExerciseKnowledgeBlankDTO dto){
        return myService.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityExerciseKnowledgeBlankDTO dto){
        return myService.change(dto);
    }

    @RequestMapping(value = "/delete/{technologyID}/{knowledgeID}/{exercisesID}/{teacherID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID,
                                  @PathVariable("knowledgeID") int knowledgeID,
                                  @PathVariable("exercisesID") int exercisesID,
                                  @PathVariable("teacherID") int teacherID){
        return myService.delete(technologyID, knowledgeID, exercisesID, teacherID);
    }
}
