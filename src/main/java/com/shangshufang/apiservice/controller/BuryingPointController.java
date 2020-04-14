package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.BusinessAnalyseLogDTO;
import com.shangshufang.apiservice.service.BusinessAnalyseLogService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/burying_point")
public class BuryingPointController {
    @Autowired
    private BusinessAnalyseLogService serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BusinessAnalyseLogDTO dto){
        return serviceImpl.add(dto);
    }
}
