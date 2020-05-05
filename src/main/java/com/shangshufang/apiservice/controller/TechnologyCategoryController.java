package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.TechnologyCategoryDTO;
import com.shangshufang.apiservice.service.impl.TechnologyCategoryServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/technology/category")
public class TechnologyCategoryController {
    @Autowired
    private TechnologyCategoryServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{directionID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("directionID") int directionID,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, directionID, dataStatus);
    }

    @RequestMapping(value = "/check/name/{directionID}/{categoryName}", method = RequestMethod.GET)
    public UnifiedResponse checkNameExist(@PathVariable("directionID") int directionID, @PathVariable("categoryName") String categoryName){
        return serviceImpl.checkNameExist(directionID, categoryName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyCategoryDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody TechnologyCategoryDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody TechnologyCategoryDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{directionID}/{technologyCategoryID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("directionID") int directionID, @PathVariable("technologyCategoryID") int technologyCategoryID){
        return serviceImpl.delete(directionID, technologyCategoryID);
    }
}
