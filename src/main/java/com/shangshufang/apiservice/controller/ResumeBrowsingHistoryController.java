package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ResumeBrowsingHistoryDTO;
import com.shangshufang.apiservice.service.impl.ResumeBrowsingHistoryServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resume/browse")
public class ResumeBrowsingHistoryController {
    @Autowired
    private ResumeBrowsingHistoryServiceImpl serviceImpl;

    @RequestMapping(value = "/count/student/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findBrowseStudentTotalCount(@PathVariable("companyID") int companyID) {
        return serviceImpl.findBrowseStudentTotalCount(companyID);
    }

    @RequestMapping(value = "/count/company/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findBrowsedByCompanyTotalCount(@PathVariable("studentID") int studentID) {
        return serviceImpl.findBrowsedByCompanyTotalCount(studentID);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findBrowseStudentList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("companyID") int companyID) {
        return serviceImpl.findBrowseStudentList(pageNumber, pageSize, companyID);
    }

    @RequestMapping(value = "/list/company/{pageNumber}/{pageSize}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findBrowsedByCompanyList(@PathVariable("pageNumber") int pageNumber,
                                                 @PathVariable("pageSize") int pageSize,
                                                 @PathVariable("studentID") int studentID) {
        return serviceImpl.findBrowsedByCompanyList(pageNumber, pageSize, studentID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ResumeBrowsingHistoryDTO dto) {
        return serviceImpl.add(dto);
    }
}
