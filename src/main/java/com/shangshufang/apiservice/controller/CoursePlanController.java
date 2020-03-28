package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CoursePlanDTO;
import com.shangshufang.apiservice.service.impl.CoursePlanServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/plan")
public class CoursePlanController {
    @Autowired
    private CoursePlanServiceImpl serviceImpl;

    @RequestMapping(value = "/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("courseID") int courseID){
        return serviceImpl.findList(universityCode, schoolID, courseID);
    }

    @RequestMapping(value="/finishClass", method = RequestMethod.PUT)
    public UnifiedResponse finishCourseClass(@RequestBody CoursePlanDTO dto){
        return serviceImpl.finishCourseClass(dto);
    }
}
