package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.DirectionDTO;
import com.shangshufang.apiservice.service.impl.DirectionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/direction")
public class DirectionController {
    @Autowired
    private DirectionServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize){
        return serviceImpl.findList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/{directionID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("directionID") int directionID){
        return serviceImpl.find(directionID);
    }

    @RequestMapping(value = "/checkDirectionName/{directionName}", method = RequestMethod.GET)
    public UnifiedResponse checkDirectionNameExist(@PathVariable("directionName") String directionName){
        return serviceImpl.checkDirectionNameExist(directionName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody DirectionDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody DirectionDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody DirectionDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/changeThumbnail", method = RequestMethod.PUT)
    public UnifiedResponse changeThumbnail(@RequestBody DirectionDTO dto){
        return serviceImpl.changeThumbnail(dto);
    }

    @RequestMapping(value = "/{directionID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("directionID") int directionID){
        return serviceImpl.delete(directionID);
    }
}
