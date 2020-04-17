package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.MajorDTO;
import com.shangshufang.apiservice.service.impl.MajorServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/major")
public class MajorController {
    @Autowired
    private MajorServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{universityCode}/{schoolID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID) {
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID);
    }

    @RequestMapping(value = "/check/name/{universityCode}/{schoolID}/{majorName}", method = RequestMethod.GET)
    public UnifiedResponse checkNameExist(@PathVariable("universityCode") int universityCode,
                                          @PathVariable("schoolID") int schoolID,
                                          @PathVariable("majorName") String majorName) {
        return serviceImpl.checkNameExist(universityCode, schoolID, majorName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody MajorDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody MajorDTO dto) {
        return serviceImpl.change(dto);
    }


    @RequestMapping(value = "/delete/{universityCode}/{schoolID}/{majorID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode,
                                  @PathVariable("schoolID") int schoolID,
                                  @PathVariable("majorID") int majorID) {
        return serviceImpl.delete(universityCode, schoolID, majorID);
    }
}
