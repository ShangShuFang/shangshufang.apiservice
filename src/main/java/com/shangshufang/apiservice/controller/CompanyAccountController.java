package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyAccountDTO;
import com.shangshufang.apiservice.dto.CompanyCustomerDTO;
import com.shangshufang.apiservice.service.impl.CompanyAccountServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/company")
public class CompanyAccountController {
    @Autowired
    private CompanyAccountServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("companyID") int companyID){
        return serviceImpl.findList(pageNumber, pageSize, companyID);
    }

    @RequestMapping(value = "/login/{cellphone}/{password}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone, @PathVariable("password") String password){
        return serviceImpl.login(cellphone, password);
    }

    @RequestMapping(value = "/check/cellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/delete/{companyID}/{customerID}/{accountID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("companyID") int companyID,
                                  @PathVariable("customerID") int customerID,
                                  @PathVariable("accountID") int accountID){
        return serviceImpl.delete(companyID, customerID, accountID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyCustomerDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CompanyCustomerDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value = "/change/password", method = RequestMethod.PUT)
    public UnifiedResponse changePassword(@RequestBody CompanyCustomerDTO dto){
        return serviceImpl.changePassword(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CompanyCustomerDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
