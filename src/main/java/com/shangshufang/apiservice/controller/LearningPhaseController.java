package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.LearningPathServiceImpl;
import com.shangshufang.apiservice.service.impl.LearningPhaseServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys_parm/learning_phase")
public class LearningPhaseController {
    @Autowired
    private LearningPhaseServiceImpl serviceImpl;

    @Autowired
    private LearningPathServiceImpl learningPathService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public UnifiedResponse findList(){
        return serviceImpl.findList();
    }

    @RequestMapping(value = "/list/has_knowledge/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhase(@PathVariable("technologyID") int technologyID){
        return learningPathService.findLearningPhase(technologyID);
    }
}
