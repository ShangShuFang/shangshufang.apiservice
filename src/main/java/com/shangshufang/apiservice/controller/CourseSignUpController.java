package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseSignUpDTO;
import com.shangshufang.apiservice.service.impl.CourseSignUpServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course/signUp")
public class CourseSignUpController {
    @Autowired
    private CourseSignUpServiceImpl serviceImpl;

    @RequestMapping(value = "/course/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findCourseSignUpList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("courseID") int courseID){
        return serviceImpl.findCourseSignUpList(pageNumber, pageSize, universityCode, schoolID, courseID);
    }

    @RequestMapping(value = "/student/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentSignUpList(@PathVariable("pageNumber") int pageNumber,
                                                @PathVariable("pageSize") int pageSize,
                                                @PathVariable("universityCode") int universityCode,
                                                @PathVariable("schoolID") int schoolID,
                                                @PathVariable("studentID") int studentID){
        return serviceImpl.findStudentSignUpList(pageNumber, pageSize, universityCode, schoolID, studentID);
    }

    @RequestMapping(value = "/technology/{pageNumber}/{pageSize}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findTechnologyCourseSignUpList(@PathVariable("pageNumber") int pageNumber,
                                                 @PathVariable("pageSize") int pageSize,
                                                 @PathVariable("technologyID") int technologyID){
        return serviceImpl.findTechnologyCourseSignUpList(pageNumber, pageSize, technologyID);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseSignUpDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/changeDataStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeDataStatus(@RequestBody CourseSignUpDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
