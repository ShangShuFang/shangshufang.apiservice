package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.VerificationCodeDTO;
import com.shangshufang.apiservice.service.impl.VerificationCodeServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verification_code")
public class VerificationCodeController {
    @Autowired
    private VerificationCodeServiceImpl serviceImpl;

    @RequestMapping(value = "/any/{cellphone}/{code}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("cellphone") String cellphone, @PathVariable("code") String code){
        return serviceImpl.find(cellphone, code);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody VerificationCodeDTO dto){
        return serviceImpl.add(dto);
    }

}
