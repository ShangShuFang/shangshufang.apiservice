package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.LearningPathDTO;
import com.shangshufang.apiservice.service.impl.LearningPathServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learningPath")
public class LearningPathController {
    @Autowired
    private LearningPathServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{technologyID}/{learningPhase}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("learningPhase") int learningPhase){
        return serviceImpl.findList(pageNumber, pageSize, technologyID, learningPhase);
    }

    @RequestMapping(value = "/usingLearningPhase/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findUsingLearningPhase(@PathVariable("technologyID") int technologyID){
        return serviceImpl.findUsingLearningPhase(technologyID);
    }

    @RequestMapping(value = "/usingKnowledge/{technologyID}/{learningPhase}", method = RequestMethod.GET)
    public UnifiedResponse findUsingKnowledge(@PathVariable("technologyID") int technologyID, @PathVariable("learningPhase") int learningPhase){
        return serviceImpl.findUsingKnowledge(technologyID, learningPhase);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody LearningPathDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody LearningPathDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody LearningPathDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/{technologyID}/{learningPhase}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID, @PathVariable("learningPhase") int learningPhase){
        return serviceImpl.delete(technologyID, learningPhase);
    }
}
