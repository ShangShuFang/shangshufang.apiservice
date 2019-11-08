package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityAccountDTO;
import com.shangshufang.apiservice.service.impl.UniversityAccountServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university/account")
public class UniversityAccountController {
    @Autowired
    private UniversityAccountServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{universityCode}/{schoolID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID){
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID);
    }

    @RequestMapping(value = "/login/{cellphone}/{password}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone, @PathVariable("password") String password){
        return serviceImpl.login(cellphone, password);
    }

    @RequestMapping(value = "/checkCellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/checkEmail/{email}", method = RequestMethod.GET)
    public UnifiedResponse checkEmailExist(@PathVariable("email") String email){
        return serviceImpl.checkEmailExist(email);
    }

    @RequestMapping(value = "/{universityCode}/{schoolID}/{accountID}/{customerID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode,
                                  @PathVariable("schoolID") int schoolID,
                                  @PathVariable("accountID") int accountID,
                                  @PathVariable("customerID") int customerID){
        return serviceImpl.delete(universityCode, schoolID, accountID, customerID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
