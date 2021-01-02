package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UserTrackingDTO;
import com.shangshufang.apiservice.service.impl.UserTrackingServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tracking")
public class UserTrackingController {
    @Autowired
    private UserTrackingServiceImpl serviceImpl;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UserTrackingDTO dto){
        return serviceImpl.add(dto);
    }

}
