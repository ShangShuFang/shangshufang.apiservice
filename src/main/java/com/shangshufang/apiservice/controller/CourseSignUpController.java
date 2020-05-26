package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseSignUpDTO;
import com.shangshufang.apiservice.service.impl.CourseSignUpServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/sign_up")
public class CourseSignUpController {
    @Autowired
    private CourseSignUpServiceImpl serviceImpl;

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findCourseSignUpList(@PathVariable("pageNumber") int pageNumber,
                                                @PathVariable("pageSize") int pageSize,
                                                @PathVariable("universityCode") int universityCode,
                                                @PathVariable("schoolID") int schoolID,
                                                @PathVariable("courseID") int courseID) {
        return serviceImpl.findCourseSignUpList(pageNumber, pageSize, universityCode, schoolID, courseID);
    }

    @RequestMapping(value = "/list/course/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentSignUpList(@PathVariable("pageNumber") int pageNumber,
                                                 @PathVariable("pageSize") int pageSize,
                                                 @PathVariable("universityCode") int universityCode,
                                                 @PathVariable("schoolID") int schoolID,
                                                 @PathVariable("studentID") int studentID) {
        return serviceImpl.findStudentSignUpList(pageNumber, pageSize, universityCode, schoolID, studentID);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findTechnologyCourseSignUpList(@PathVariable("pageNumber") int pageNumber,
                                                          @PathVariable("pageSize") int pageSize,
                                                          @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findTechnologyCourseSignUpList(pageNumber, pageSize, technologyID);
    }

    @RequestMapping(value = "/check/sign_up/{studentID}/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentSignUpList(@PathVariable("studentID") int studentID,
                                                 @PathVariable("universityCode") int universityCode,
                                                 @PathVariable("schoolID") int schoolID,
                                                 @PathVariable("courseID") int courseID) {
        return serviceImpl.checkIsSignUpCourse(studentID, universityCode, schoolID, courseID);
    }

    @RequestMapping(value = "/check/assistant/{studentID}/{universityCode}/{schoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse checkIsAssistant(@PathVariable("studentID") int studentID,
                                            @PathVariable("universityCode") int universityCode,
                                            @PathVariable("schoolID") int schoolID,
                                            @PathVariable("courseID") int courseID) {
        return serviceImpl.checkIsAssistant(studentID, universityCode, schoolID, courseID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseSignUpDTO dto) {
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change/assistant", method = RequestMethod.PUT)
    public UnifiedResponse changeAssistant(@RequestBody CourseSignUpDTO dto) {
        return serviceImpl.changeAssistant(dto);
    }

    @RequestMapping(value = "/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeDataStatus(@RequestBody CourseSignUpDTO dto) {
        return serviceImpl.changeDataStatus(dto);
    }
}
