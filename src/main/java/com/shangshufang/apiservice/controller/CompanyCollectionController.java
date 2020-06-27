package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyCollectionDTO;
import com.shangshufang.apiservice.service.impl.CompanyCollectionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company/collection")
public class CompanyCollectionController {
    @Autowired
    private CompanyCollectionServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findList(pageNumber, pageSize, studentID);
    }

    @RequestMapping(value = "/check/collected/{companyID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse checkCollected(@PathVariable("companyID") int companyID, @PathVariable("studentID") int studentID) {
        return serviceImpl.checkCollected(companyID, studentID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyCollectionDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/delete/{companyID}/{studentID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("companyID") int companyID, @PathVariable("studentID") int studentID) {
        return serviceImpl.delete(companyID, studentID);
    }
}
