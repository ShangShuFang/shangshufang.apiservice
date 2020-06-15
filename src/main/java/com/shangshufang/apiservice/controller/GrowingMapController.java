package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.GrowingMapDTO;
import com.shangshufang.apiservice.service.impl.GrowingMapServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/growing_map")
public class GrowingMapController {
    @Autowired
    private GrowingMapServiceImpl serviceImpl;

    @RequestMapping(value = "/list/map/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public UnifiedResponse findMapList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize) {
        return serviceImpl.findMapList(pageNumber, pageSize);
    }

    @RequestMapping(value = "/any/map/{growingID}", method = RequestMethod.GET)
    public UnifiedResponse findMap(@PathVariable("growingID") int growingID) {
        return serviceImpl.findMap(growingID);
    }

    @RequestMapping(value = "/list/detail/{growingID}", method = RequestMethod.GET)
    public UnifiedResponse findMapDetailList(@PathVariable("growingID") int growingID) {
        return serviceImpl.findMapDetailList(growingID);
    }

    @RequestMapping(value = "/list/learningPhase/{growingID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhaseList(@PathVariable("growingID") int growingID) {
        return serviceImpl.findLearningPhaseList(growingID);
    }

    @RequestMapping(value = "/check/name/{growingTarget}", method = RequestMethod.GET)
    public UnifiedResponse checkGrowingTargetExist(@PathVariable("growingTarget") String growingTarget){
        return serviceImpl.checkGrowingTargetExist(growingTarget);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UnifiedResponse save(@RequestBody GrowingMapDTO dto){
        return serviceImpl.save(dto);
    }

    @RequestMapping(value = "/delete/{growingID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("growingID") int growingID){
        return serviceImpl.delete(growingID);
    }

}
