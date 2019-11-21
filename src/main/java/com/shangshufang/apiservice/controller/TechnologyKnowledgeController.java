package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeDTO;
import com.shangshufang.apiservice.service.impl.TechnologyKnowledgeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technology/knowledge")
public class TechnologyKnowledgeController {
    @Autowired
    private TechnologyKnowledgeServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID){
        return serviceImpl.findList(pageNumber, pageSize, technologyID);
    }

    @RequestMapping(value = "/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("technologyID") int technologyID){
        return serviceImpl.find(technologyID);
    }

    @RequestMapping(value = "/checkKnowledgeName/{technologyID}/{knowledgeName}", method = RequestMethod.GET)
    public UnifiedResponse checkKnowledgeNameExist(@PathVariable("technologyID") int technologyID, @PathVariable("knowledgeName") String knowledgeName){
        return serviceImpl.checkKnowledgeNameExist(technologyID, knowledgeName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody TechnologyKnowledgeDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/{technologyID}/{knowledgeID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID, @PathVariable("knowledgeID") int knowledgeID){
        return serviceImpl.delete(technologyID, knowledgeID);
    }
}
