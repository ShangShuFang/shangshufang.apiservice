package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyDTO;
import com.shangshufang.apiservice.service.impl.TechnologyServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/technology")
public class TechnologyController {
    @Autowired
    private TechnologyServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/client/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList4Client(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList4Client(pageNumber, pageSize);
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public UnifiedResponse findSimpleList(){
        return serviceImpl.findSimpleList();
    }

    @RequestMapping(value = "/learning/{studentUniversityCode}/{studentSchoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentLearning(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                               @PathVariable("studentSchoolID") int studentSchoolID,
                                               @PathVariable("studentID") int studentID){
        return serviceImpl.findStudentLearning(studentUniversityCode, studentSchoolID, studentID);
    }

    @RequestMapping(value = "/any/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("technologyID") int technologyID){
        return serviceImpl.find(technologyID);
    }

    @RequestMapping(value = "/check/name/{technologyName}", method = RequestMethod.GET)
    public UnifiedResponse checkTechnologyNameExist(@PathVariable("technologyName") String technologyName){
        return serviceImpl.checkTechnologyNameExist(technologyName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change",method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody TechnologyDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/change/thumbnail", method = RequestMethod.PUT)
    public UnifiedResponse changeThumbnail(@RequestBody TechnologyDTO dto){
        return serviceImpl.changeThumbnail(dto);
    }

    @RequestMapping(value = "/delete/{technologyID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID){
        return serviceImpl.delete(technologyID);
    }
}
