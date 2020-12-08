package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ThirdPartyServicesDTO;
import com.shangshufang.apiservice.service.impl.ThirdPartyServicesImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/thirdParty/service")
public class ThirdPartyServicesController {
    @Autowired
    private ThirdPartyServicesImpl serviceImpl;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ThirdPartyServicesDTO dto){
        return serviceImpl.add(dto);
    }
}
