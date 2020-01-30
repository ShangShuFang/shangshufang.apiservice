package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.BusinessAnalyseLogDTO;
import com.shangshufang.apiservice.service.BusinessAnalyseLogService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businessAnalyseLog")
public class BusinessAnalyseLogController {
    @Autowired
    private BusinessAnalyseLogService serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody BusinessAnalyseLogDTO dto){
        return serviceImpl.add(dto);
    }
}
