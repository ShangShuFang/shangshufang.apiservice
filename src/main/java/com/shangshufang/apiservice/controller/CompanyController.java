package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyDTO;
import com.shangshufang.apiservice.service.impl.CompanyServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{provinceCode}/{cityCode}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("provinceCode") int provinceCode,
                                    @PathVariable("cityCode") int cityCode){
        return serviceImpl.findList(pageNumber, pageSize, provinceCode, cityCode);
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public UnifiedResponse findList4Client(){
        return serviceImpl.findList4Client();
    }

    @RequestMapping(value = "/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("companyID") int companyID){
        return serviceImpl.find(companyID);
    }

    @RequestMapping(value = "/checkCompanyNameExist/{companyName}", method = RequestMethod.GET)
    public UnifiedResponse checkCompanyNameExist(@PathVariable("companyName") String companyName){
        return serviceImpl.checkCompanyNameExist(companyName);
    }

    @RequestMapping(value = "/checkCellphoneExist/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CompanyDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CompanyDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changeBrand", method = RequestMethod.PUT)
    public UnifiedResponse changeBrand(@RequestBody CompanyDTO dto){
        return serviceImpl.changeBrand(dto);
    }

    @RequestMapping(value="/changeMemo", method = RequestMethod.PUT)
    public UnifiedResponse changeMemo(@RequestBody CompanyDTO dto){
        return serviceImpl.changeMemo(dto);
    }

    @RequestMapping(value="/changeRecruitLevel", method = RequestMethod.PUT)
    public UnifiedResponse changeRecruitLevel(@RequestBody CompanyDTO dto){
        return serviceImpl.changeRecruitLevel(dto);
    }

    @RequestMapping(value = "/{companyID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("companyID") int companyID){
        return serviceImpl.delete(companyID);
    }
}
