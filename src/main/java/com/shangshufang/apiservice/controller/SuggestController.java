package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.SuggestDTO;
import com.shangshufang.apiservice.service.impl.SuggestServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suggest")
public class SuggestController {
    @Autowired
    private SuggestServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{portal}/{cellphone}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("portal") String portal,
                                    @PathVariable("cellphone") String cellphone,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, portal, cellphone, dataStatus);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody SuggestDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody SuggestDTO dto){
        return serviceImpl.change(dto);
    }


}
