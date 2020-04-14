package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.DirectionDTO;
import com.shangshufang.apiservice.service.impl.DirectionServiceImpl;
import com.shangshufang.apiservice.service.impl.TechnologyDirectionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/develop_direction")
public class DirectionController {
    @Autowired
    private DirectionServiceImpl serviceImpl;

    @Autowired
    private TechnologyDirectionServiceImpl technologyDirectionServiceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, dataStatus);
    }

    @RequestMapping(value = "/any/{directionID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("directionID") int directionID){
        return serviceImpl.find(directionID);
    }

    @RequestMapping(value = "/check/name/{directionName}", method = RequestMethod.GET)
    public UnifiedResponse checkDirectionNameExist(@PathVariable("directionName") String directionName){
        return serviceImpl.checkDirectionNameExist(directionName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody DirectionDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody DirectionDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody DirectionDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/change/thumbnail", method = RequestMethod.PUT)
    public UnifiedResponse changeThumbnail(@RequestBody DirectionDTO dto){
        return serviceImpl.changeThumbnail(dto);
    }

    @RequestMapping(value = "/delete/{directionID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("directionID") int directionID){
        return serviceImpl.delete(directionID);
    }

    @RequestMapping(value = "/list/direction/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Technology(@PathVariable("technologyID") int technologyID){
        return technologyDirectionServiceImpl.findList4Technology(technologyID);
    }

    @RequestMapping(value = "/list/technology/{directionID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Direction(@PathVariable("directionID") int directionID){
        return technologyDirectionServiceImpl.findList4Direction(directionID);
    }
}
