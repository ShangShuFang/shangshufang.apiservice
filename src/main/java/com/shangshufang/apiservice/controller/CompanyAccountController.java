package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyAccountDTO;
import com.shangshufang.apiservice.service.impl.CompanyAccountServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company/account")
public class CompanyAccountController {
    @Autowired
    private CompanyAccountServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("companyID") int companyID){
        return serviceImpl.findList(pageNumber, pageSize, companyID);
    }

    @RequestMapping(value = "/login/{cellphone}/{password}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone, @PathVariable("password") String password){
        return serviceImpl.login(cellphone, password);
    }

    @RequestMapping(value = "/checkCellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/{companyID}/{customerID}/{accountID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("companyID") int companyID,
                                  @PathVariable("customerID") int customerID,
                                  @PathVariable("accountID") int accountID){
        return serviceImpl.delete(companyID, customerID, accountID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyAccountDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CompanyAccountDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CompanyAccountDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
