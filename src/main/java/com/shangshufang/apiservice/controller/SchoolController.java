package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.SchoolDTO;
import com.shangshufang.apiservice.service.impl.SchoolServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/school")
public class SchoolController {
    @Autowired
    private SchoolServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{universityCode}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, universityCode, dataStatus);
    }

    @RequestMapping(value = "/any/{universityCode}/{schoolID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode, @PathVariable("schoolID") int schoolID){
        return serviceImpl.find(universityCode, schoolID);
    }

    @RequestMapping(value = "/check/name/{universityCode}/{schoolName}", method = RequestMethod.GET)
    public UnifiedResponse checkSchoolNameExist(@PathVariable("universityCode") int universityCode, @PathVariable("schoolName") String schoolName){
        return serviceImpl.checkSchoolNameExist(universityCode, schoolName);
    }

    @RequestMapping(value = "/check/cellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphone(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody SchoolDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody SchoolDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody SchoolDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{universityCode}/{schoolID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode, @PathVariable("schoolID") int schoolID){
        return serviceImpl.delete(universityCode, schoolID);
    }
}
