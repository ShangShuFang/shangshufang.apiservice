package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyUsingDTO;
import com.shangshufang.apiservice.service.impl.TechnologyUsingServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technology/using")
public class TechnologyUsingController {
    @Autowired
    private TechnologyUsingServiceImpl serviceImpl;

    @RequestMapping(value = "/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findUsingTechnologyList(@PathVariable("companyID") int companyID){
        return serviceImpl.findUsingTechnologyList(companyID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyUsingDTO dto){
        return serviceImpl.add(dto);
    }
}
