package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.StudentCollectionDTO;
import com.shangshufang.apiservice.service.impl.StudentCollectionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/collection")
public class StudentCollectionController {
    @Autowired
    private StudentCollectionServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findList(pageNumber, pageSize, studentID);
    }

    @RequestMapping(value = "/check/collected/{studentID}/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse checkCollected(@PathVariable("studentID") int studentID,
                                          @PathVariable("companyID") int companyID) {
        return serviceImpl.checkCollected(studentID, companyID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody StudentCollectionDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/delete/{studentID}/{companyID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("studentID") int studentID,
                                  @PathVariable("companyID") int companyID) {
        return serviceImpl.delete(studentID, companyID);
    }
}
