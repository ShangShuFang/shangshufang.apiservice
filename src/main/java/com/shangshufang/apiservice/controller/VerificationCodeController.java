package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.VerificationCodeDTO;
import com.shangshufang.apiservice.service.impl.VerificationCodeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verificationCode")
public class VerificationCodeController {
    @Autowired
    private VerificationCodeServiceImpl serviceImpl;

    @RequestMapping(value = "/{cellphone}/{code}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("cellphone") String cellphone, @PathVariable("code") String code){
        return serviceImpl.find(cellphone, code);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody VerificationCodeDTO dto){
        return serviceImpl.add(dto);
    }

}
