package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.LearningPathServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learningPath")
public class LearningPathController {
    @Autowired
    private LearningPathServiceImpl serviceImpl;

    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    public UnifiedResponse findTechnology(){
        return serviceImpl.findTechnology();
    }

    @RequestMapping(value = "/learningPhase/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhase(@PathVariable("technologyID") int technologyID){
        return serviceImpl.findLearningPhase(technologyID);
    }

    @RequestMapping(value = "/knowledge/{technologyID}/{learningPhase}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledge(@PathVariable("technologyID") int technologyID, @PathVariable("learningPhase") int learningPhase){
        return serviceImpl.findKnowledge(technologyID, learningPhase);
    }
}
