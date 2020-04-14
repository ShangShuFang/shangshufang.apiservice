package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.SuggestTypeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys_parm/suggest_type")
public class SuggestTypeController {
    @Autowired
    private SuggestTypeServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{portal}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("portal") String portal){
        return serviceImpl.findList(portal);
    }
}
