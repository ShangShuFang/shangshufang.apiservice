package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseQuestionDTO;
import com.shangshufang.apiservice.service.impl.CourseQuestionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/question")
public class CourseQuestionController {
    @Autowired
    private CourseQuestionServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{courseUniversityCode}/{courseSchoolID}/{courseID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("courseUniversityCode") int courseUniversityCode,
                                    @PathVariable("courseSchoolID") int courseSchoolID,
                                    @PathVariable("courseID") int courseID) {
        return serviceImpl.findList(pageNumber, pageSize, courseUniversityCode, courseSchoolID, courseID);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Student(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findList4Student(pageNumber, pageSize, studentID);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseQuestionDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CourseQuestionDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
