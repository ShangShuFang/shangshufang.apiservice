package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CodeStandardDTO;
import com.shangshufang.apiservice.service.impl.CodeStandardServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/code_standard")
public class CodeStandardController {
    @Autowired
    private CodeStandardServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{languageID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("languageID") int languageID){
        return serviceImpl.findList(pageNumber, pageSize, languageID);
    }

    @RequestMapping(value = "/list/technology/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("technologyID") int technologyID){
        return serviceImpl.findList(technologyID);
    }

    @RequestMapping(value = "/check/name/{languageID}/{codeStandardName}", method = RequestMethod.GET)
    public UnifiedResponse checkNameExist(@PathVariable("languageID") int languageID, @PathVariable("codeStandardName") String codeStandardName){
        return serviceImpl.checkNameExist(languageID, codeStandardName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CodeStandardDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CodeStandardDTO dto){
        return serviceImpl.change(dto);
    }


    @RequestMapping(value = "/delete/{languageID}/{codeStandardID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("languageID") int languageID, @PathVariable("codeStandardID") int codeStandardID){
        return serviceImpl.delete(languageID, codeStandardID);
    }
}
