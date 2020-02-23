package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyDTO;
import com.shangshufang.apiservice.service.impl.TechnologyServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technology")
public class TechnologyController {
    @Autowired
    private TechnologyServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/client/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList4Client(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList4Client(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("technologyID") int technologyID){
        return serviceImpl.find(technologyID);
    }

    @RequestMapping(value = "/checkTechnologyName/{technologyName}", method = RequestMethod.GET)
    public UnifiedResponse checkTechnologyNameExist(@PathVariable("technologyName") String technologyName){
        return serviceImpl.checkTechnologyNameExist(technologyName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody TechnologyDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changeThumbnail", method = RequestMethod.PUT)
    public UnifiedResponse changeThumbnail(@RequestBody TechnologyDTO dto){
        return serviceImpl.changeThumbnail(dto);
    }

    @RequestMapping(value = "/{technologyID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID){
        return serviceImpl.delete(technologyID);
    }
}
