package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.service.impl.CourseServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{teacherID}/{courseTimeBegin}/{courseTimeEnd}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("teacherID") int teacherID,
                                    @PathVariable("courseTimeBegin") String courseTimeBegin,
                                    @PathVariable("courseTimeEnd") String courseTimeEnd,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID, teacherID, courseTimeBegin, courseTimeEnd, dataStatus);
    }

    @RequestMapping(value = "/{universityCode}/{schoolID}/{teacherID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode,
                                @PathVariable("schoolID") int schoolID,
                                @PathVariable("teacherID") int teacherID,
                                @PathVariable("courseID") int courseID) {
        return serviceImpl.find(universityCode, schoolID, teacherID, courseID);
    }

    @RequestMapping(value = "/checkCourse/{universityCode}/{schoolID}/{courseName}/{courseTimeBegin}/{courseTimeEnd}", method = RequestMethod.GET)
    public UnifiedResponse checkCourseExist(@PathVariable("universityCode") int universityCode,
                                            @PathVariable("schoolID") int schoolID,
                                            @PathVariable("courseName") String courseName,
                                            @PathVariable("courseTimeBegin") String courseTimeBegin,
                                            @PathVariable("courseTimeEnd") String courseTimeEnd) {
        return serviceImpl.checkCourseExist(universityCode, schoolID, courseName, courseTimeBegin, courseTimeEnd);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CourseDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CourseDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}