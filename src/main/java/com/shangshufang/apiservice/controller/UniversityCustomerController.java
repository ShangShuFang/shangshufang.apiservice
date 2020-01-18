package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityCustomerDTO;
import com.shangshufang.apiservice.service.impl.UniversityCustomerServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university/customer")
public class UniversityCustomerController {
    @Autowired
    private UniversityCustomerServiceImpl serviceImpl;

    @RequestMapping(value = "/{universityCode}/{schoolID}/{customerRole}/{fullName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("customerRole") String customerRole,
                                    @PathVariable("fullName") String fullName){
        return serviceImpl.findList(universityCode, schoolID, customerRole, fullName);
    }

    @RequestMapping(value = "/{customerID}/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("customerID") int customerID, @PathVariable("cellphone") String cellphone){
        return serviceImpl.find(customerID, cellphone);
    }

    @RequestMapping(value = "/checkCellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/checkEmail/{email}", method = RequestMethod.GET)
    public UnifiedResponse checkEmailExist(@PathVariable("email") String email){
        return serviceImpl.checkEmailExist(email);
    }

    @RequestMapping(value = "/{universityCode}/{schoolID}/{customerID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode,
                                  @PathVariable("schoolID") int schoolID,
                                  @PathVariable("customerID") int customerID){
        return serviceImpl.delete(universityCode, schoolID, customerID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityCustomerDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityCustomerDTO dto){
        return serviceImpl.change(dto);
    }
}
