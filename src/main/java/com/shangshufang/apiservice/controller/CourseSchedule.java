package com.shangshufang.apiservice.controller;


import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.service.impl.CourseServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course/schedule")
public class CourseSchedule {
    @Autowired
    private CourseServiceImpl serviceImpl;

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse changeCourseSchedule(@RequestBody CourseDTO dto){
        return serviceImpl.changeCourseSchedule(dto);
    }
}
