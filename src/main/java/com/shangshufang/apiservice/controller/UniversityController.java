package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityDTO;
import com.shangshufang.apiservice.service.impl.UniversityServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university")
public class UniversityController {
    @Autowired
    private UniversityServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{provinceCode}/{cityCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("provinceCode") int provinceCode,
                                    @PathVariable("cityCode") int cityCode){
        return serviceImpl.findList(pageNumber, pageSize, provinceCode, cityCode);
    }

    @RequestMapping(value = "/{universityCode}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode){
        return serviceImpl.find(universityCode);
    }

    @RequestMapping(value = "/checkUniversityCode/{universityCode}", method = RequestMethod.GET)
    public UnifiedResponse checkUniversityCodeExist(@PathVariable("universityCode") String universityCode){
        return serviceImpl.checkUniversityCodeExist(universityCode);
    }

    @RequestMapping(value = "/checkUniversityName/{universityName}", method = RequestMethod.GET)
    public UnifiedResponse checkUniversityNameExist(@PathVariable("universityName") String universityName){
        return serviceImpl.checkUniversityNameExist(universityName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changeBrand", method = RequestMethod.PUT)
    public UnifiedResponse changeBrand(@RequestBody UniversityDTO dto){
        return serviceImpl.changeBrand(dto);
    }

    @RequestMapping(value="/changeMemo", method = RequestMethod.PUT)
    public UnifiedResponse changeMemo(@RequestBody UniversityDTO dto){
        return serviceImpl.changeMemo(dto);
    }

    @RequestMapping(value = "/{universityID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityID") int universityID){
        return serviceImpl.delete(universityID);
    }
}
