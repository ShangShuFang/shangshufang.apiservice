package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyCodeStandardDTO;
import com.shangshufang.apiservice.service.impl.TechnologyCodeStandardServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codeStandard")
public class TechnologyCodeStandardController {
    @Autowired
    private TechnologyCodeStandardServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID){
        return serviceImpl.findList(pageNumber, pageSize, technologyID);
    }

    @RequestMapping(value = "/checkCodeStandard/{technologyID}/{codeStandardName}", method = RequestMethod.GET)
    public UnifiedResponse checkCodeStandardExist(@PathVariable("technologyID") int technologyID, @PathVariable("codeStandardName") String codeStandardName){
        return serviceImpl.checkCodeStandardExist(technologyID, codeStandardName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyCodeStandardDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyCodeStandardDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/{technologyID}/{codeStandardID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID, @PathVariable("codeStandardID") int codeStandardID){
        return serviceImpl.delete(technologyID, codeStandardID);
    }
}
