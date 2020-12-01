package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.TechnologyServiceImpl;
import com.shangshufang.apiservice.service.impl.TechnologyUsingServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/technology/using")
public class TechnologyUsingController {
    @Autowired
    private TechnologyUsingServiceImpl serviceImpl;

    @RequestMapping(value = "/company/list/{pageNumber}/{pageSize}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findCompanyList(pageNumber, pageSize, technologyID);
    }
}
