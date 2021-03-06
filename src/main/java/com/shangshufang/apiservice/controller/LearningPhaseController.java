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

    @RequestMapping(value = "/list/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("technologyID") int technologyID) {
        return serviceImpl.findList(technologyID);
    }

    @RequestMapping(value = "/list/learningPhase", method = RequestMethod.GET)
    public UnifiedResponse findAllLearningPhase() {
        return learningPathService.findAllLearningPhase();
    }

    @RequestMapping(value = "/list/has_knowledge/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhase(@PathVariable("technologyID") int technologyID) {
        return learningPathService.findLearningPhase(technologyID);
    }
}
