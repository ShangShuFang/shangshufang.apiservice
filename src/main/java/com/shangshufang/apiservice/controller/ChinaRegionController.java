package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.ChinaRegionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chinaRegion")
public class ChinaRegionController {
    @Autowired
    private ChinaRegionServiceImpl serviceImpl;

    @RequestMapping(value = "/{regionParentCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("regionParentCode") int regionParentCode){
        return serviceImpl.findList(regionParentCode);
    }
}
