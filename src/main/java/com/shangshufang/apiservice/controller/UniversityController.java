package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityDTO;
import com.shangshufang.apiservice.service.impl.UniversityServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/university")
public class UniversityController {
    @Autowired
    private UniversityServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{provinceCode}/{cityCode}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("provinceCode") int provinceCode,
                                    @PathVariable("cityCode") int cityCode,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, provinceCode, cityCode, dataStatus);
    }

    @RequestMapping(value = "/any/{universityCode}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode){
        return serviceImpl.find(universityCode);
    }

    @RequestMapping(value = "/check/code/{universityCode}", method = RequestMethod.GET)
    public UnifiedResponse checkUniversityCodeExist(@PathVariable("universityCode") String universityCode){
        return serviceImpl.checkUniversityCodeExist(universityCode);
    }

    @RequestMapping(value = "/check/name/{universityName}", method = RequestMethod.GET)
    public UnifiedResponse checkUniversityNameExist(@PathVariable("universityName") String universityName){
        return serviceImpl.checkUniversityNameExist(universityName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/change/logo", method = RequestMethod.PUT)
    public UnifiedResponse changeBrand(@RequestBody UniversityDTO dto){
        return serviceImpl.changeBrand(dto);
    }

    @RequestMapping(value="/change/memo", method = RequestMethod.PUT)
    public UnifiedResponse changeMemo(@RequestBody UniversityDTO dto){
        return serviceImpl.changeMemo(dto);
    }

    @RequestMapping(value = "/delete/{universityID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityID") int universityID){
        return serviceImpl.delete(universityID);
    }
}
