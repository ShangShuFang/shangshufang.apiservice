package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.TechnologyDirectionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/technology/direction")
public class TechnologyDirectionController {
    @Autowired
    private TechnologyDirectionServiceImpl serviceImpl;

    @RequestMapping(value = "/forTechnology/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Technology(@PathVariable("technologyID") int technologyID){
        return serviceImpl.findList4Technology(technologyID);
    }

    @RequestMapping(value = "/forDirection/{directionID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Direction(@PathVariable("directionID") int directionID){
        return serviceImpl.findList4Direction(directionID);
    }
}
