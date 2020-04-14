package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeDTO;
import com.shangshufang.apiservice.service.impl.LearningPathServiceImpl;
import com.shangshufang.apiservice.service.impl.TechnologyKnowledgeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/knowledge")
public class TechnologyKnowledgeController {
    @Autowired
    private TechnologyKnowledgeServiceImpl serviceImpl;

    @Autowired
    private LearningPathServiceImpl learningPathServiceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{technologyID}/{learningPhaseID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("learningPhaseID") int learningPhaseID,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, technologyID, learningPhaseID, dataStatus);
    }

    @RequestMapping(value = "/list/learningPhase/{technologyID}/{learningPhase}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledge(@PathVariable("technologyID") int technologyID, @PathVariable("learningPhase") int learningPhase){
        return learningPathServiceImpl.findKnowledge(technologyID, learningPhase);
    }

    @RequestMapping(value = "/list/simple/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("technologyID") int technologyID){
        return serviceImpl.findList(technologyID);
    }

    @RequestMapping(value = "/check/name/{technologyID}/{knowledgeName}", method = RequestMethod.GET)
    public UnifiedResponse checkKnowledgeNameExist(@PathVariable("technologyID") int technologyID, @PathVariable("knowledgeName") String knowledgeName){
        return serviceImpl.checkKnowledgeNameExist(technologyID, knowledgeName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{technologyID}/{learningPhaseID}/{knowledgeID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID,
                                  @PathVariable("learningPhaseID") int learningPhaseID,
                                  @PathVariable("knowledgeID") int knowledgeID){
        return serviceImpl.delete(technologyID, learningPhaseID, knowledgeID);
    }
}
