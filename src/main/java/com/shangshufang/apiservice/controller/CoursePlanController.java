package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CoursePlanDTO;
import com.shangshufang.apiservice.service.impl.CoursePlanServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course/plan")
public class CoursePlanController {
    @Autowired
    private CoursePlanServiceImpl serviceImpl;

    @RequestMapping(value="/finishClass", method = RequestMethod.PUT)
    public UnifiedResponse finishCourseClass(@RequestBody CoursePlanDTO dto){
        return serviceImpl.finishCourseClass(dto);
    }
}
