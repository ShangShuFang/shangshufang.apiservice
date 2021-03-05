package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyTalentPoolDTO;
import com.shangshufang.apiservice.service.impl.CompanyTalentPoolServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/talent")
public class CompanyTalentPoolController {
    @Autowired
    private CompanyTalentPoolServiceImpl serviceImpl;

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{companyID}/{technologyID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findListWithCompany(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("companyID") int companyID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findListWithCompany(pageNumber, pageSize, companyID, technologyID, dataStatus);
    }

    @RequestMapping(value = "/list/company/{pageNumber}/{pageSize}/{studentID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findListWithStudent(@PathVariable("pageNumber") int pageNumber,
                                               @PathVariable("pageSize") int pageSize,
                                               @PathVariable("studentID") int studentID,
                                               @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findListWithStudent(pageNumber, pageSize, studentID, dataStatus);
    }

    @RequestMapping(value = "/any/{companyID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("companyID") int companyID,
                                @PathVariable("studentID") int studentID) {
        return serviceImpl.find(companyID, studentID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyTalentPoolDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CompanyTalentPoolDTO dto) {
        return serviceImpl.change(dto);
    }
    @RequestMapping(value = "/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeDataStatus(@RequestBody CompanyTalentPoolDTO dto) {
        return serviceImpl.changeDataStatus(dto);
    }
}
