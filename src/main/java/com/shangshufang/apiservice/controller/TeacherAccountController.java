package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityAccountDTO;
import com.shangshufang.apiservice.service.impl.UniversityAccountServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account/teacher")
public class TeacherAccountController {
    @Autowired
    private UniversityAccountServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{universityCode}/{schoolID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID){
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID);
    }

    @RequestMapping(value = "/client/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{accountID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList4Client(@PathVariable("pageNumber") int pageNumber,
                                           @PathVariable("pageSize") int pageSize,
                                           @PathVariable("universityCode") int universityCode,
                                           @PathVariable("schoolID") int schoolID,
                                           @PathVariable("accountID") int accountID,
                                           @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList4Client(pageNumber, pageSize, universityCode, schoolID, accountID, dataStatus);
    }

    @RequestMapping(value = "/client/waitApprove/{universityCode}/{schoolID}/{teacherID}", method = RequestMethod.GET)
    public UnifiedResponse findWaitApproveTotalCount4Client(@PathVariable("universityCode") int universityCode,
                                           @PathVariable("schoolID") int schoolID,
                                           @PathVariable("teacherID") int teacherID){
        return serviceImpl.findWaitApproveTotalCount4Client(universityCode, schoolID, teacherID);
    }

    @RequestMapping(value = "/login/{cellphone}/{password}/{accountRole}", method = RequestMethod.GET)
    public UnifiedResponse login(@PathVariable("cellphone") String cellphone,
                                 @PathVariable("password") String password,
                                 @PathVariable("accountRole") String accountRole){
        return serviceImpl.login(cellphone, password, accountRole);
    }

    @RequestMapping(value = "/check/cellphone/register/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphone4Register(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphone4Register(cellphone);
    }

    @RequestMapping(value = "/check/cellphone/change_password/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphone4ChangePassword(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphone4ChangePassword(cellphone);
    }

    @RequestMapping(value = "/check/email/{email}", method = RequestMethod.GET)
    public UnifiedResponse checkEmailExist(@PathVariable("email") String email){
        return serviceImpl.checkEmailExist(email);
    }

    @RequestMapping(value = "/delete/{universityCode}/{schoolID}/{accountID}/{customerID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("universityCode") int universityCode,
                                  @PathVariable("schoolID") int schoolID,
                                  @PathVariable("accountID") int accountID,
                                  @PathVariable("customerID") int customerID){
        return serviceImpl.delete(universityCode, schoolID, accountID, customerID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/password",method = RequestMethod.PUT)
    public UnifiedResponse changePassword(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.changePassword(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody UniversityAccountDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
