package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityCustomerDTO;
import com.shangshufang.apiservice.service.impl.UniversityCustomerServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/teacher")
public class UniversityCustomerController {
    @Autowired
    private UniversityCustomerServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{universityCode}/{schoolID}/{fullName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("fullName") String fullName){
        return serviceImpl.findList(universityCode, schoolID, fullName);
    }

    @RequestMapping(value = "/any/{customerID}/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("customerID") int customerID, @PathVariable("cellphone") String cellphone){
        return serviceImpl.find(customerID, cellphone);
    }

    @RequestMapping(value = "/check/cellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/check/email/{email}", method = RequestMethod.GET)
    public UnifiedResponse checkEmailExist(@PathVariable("email") String email){
        return serviceImpl.checkEmailExist(email);
    }

    @RequestMapping(value = "/delete/{universityCode}/{schoolID}/{teacherID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode,
                                  @PathVariable("schoolID") int schoolID,
                                  @PathVariable("teacherID") int teacherID){
        return serviceImpl.delete(universityCode, schoolID, teacherID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityCustomerDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityCustomerDTO dto){
        return serviceImpl.change(dto);
    }
}
