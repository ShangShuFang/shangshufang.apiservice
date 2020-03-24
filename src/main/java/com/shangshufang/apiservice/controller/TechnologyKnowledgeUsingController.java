package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeUsingDTO;
import com.shangshufang.apiservice.service.impl.TechnologyKnowledgeUsingServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technology/knowledge/using")
public class TechnologyKnowledgeUsingController {
    @Autowired
    private TechnologyKnowledgeUsingServiceImpl serviceImpl;

    @RequestMapping(value = "/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("companyID") int companyID){
        return serviceImpl.findList(companyID);
    }

    @RequestMapping(value = "/technology/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findTechnologyList(@PathVariable("companyID") int companyID){
        return serviceImpl.findTechnologyList(companyID);
    }

    @RequestMapping(value = "/learningPhase/{companyID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhaseList(@PathVariable("companyID") int companyID, @PathVariable("technologyID") int technologyID){
        return serviceImpl.findLearningPhaseList(companyID, technologyID);
    }

    @RequestMapping(value = "/knowledge/{companyID}/{technologyID}/{learningPhaseID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeList(@PathVariable("companyID") int companyID,
                                             @PathVariable("technologyID") int technologyID,
                                             @PathVariable("learningPhaseID") int learningPhaseID){
        return serviceImpl.findKnowledgeList(companyID, technologyID, learningPhaseID);
    }

    @RequestMapping(value = "/client/{companyID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeList4Client(@PathVariable("companyID") int companyID, @PathVariable("technologyID") int technologyID){
        return serviceImpl.findKnowledgeList4Client(companyID, technologyID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyKnowledgeUsingDTO dto){
        return serviceImpl.add(dto);
    }
}
