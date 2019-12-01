package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.LearningPhaseServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/learningPhase")
public class LearningPhaseController {
    @Autowired
    private LearningPhaseServiceImpl serviceImpl;

    @RequestMapping(method = RequestMethod.GET)
    public UnifiedResponse findList(){
        return serviceImpl.findList();
    }
}
