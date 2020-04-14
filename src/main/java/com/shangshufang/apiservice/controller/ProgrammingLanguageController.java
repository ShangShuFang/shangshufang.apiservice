package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.ProgrammingLanguageServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys_parm/prog_lan")
public class ProgrammingLanguageController {
    @Autowired
    private ProgrammingLanguageServiceImpl serviceImpl;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public UnifiedResponse findList(){
        return serviceImpl.findList();
    }
}
