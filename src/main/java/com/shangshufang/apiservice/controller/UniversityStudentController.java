package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityStudentDTO;
import com.shangshufang.apiservice.service.impl.UniversityStudentServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university/student")
public class UniversityStudentController {
    @Autowired
    private UniversityStudentServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{fullName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("fullName") String fullName){
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID, fullName);
    }

    @RequestMapping(value = "/{universityCode}/{schoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode,
                                @PathVariable("schoolID") int schoolID,
                                @PathVariable("studentID") int studentID){
        return serviceImpl.find(universityCode, schoolID, studentID);
    }

    @RequestMapping(value = "/checkCellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/checkEmail/{email}", method = RequestMethod.GET)
    public UnifiedResponse checkEmailExist(@PathVariable("email") String email){
        return serviceImpl.checkEmailExist(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityStudentDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityStudentDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public UnifiedResponse changePassword(@RequestBody UniversityStudentDTO dto){
        return serviceImpl.changePassword(dto);
    }

    @RequestMapping(value = "/changeDataStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeDataStatus(@RequestBody UniversityStudentDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/changeAssistant", method = RequestMethod.PUT)
    public UnifiedResponse changeAssistant(@RequestBody UniversityStudentDTO dto){
        return serviceImpl.changeAssistant(dto);
    }
}
