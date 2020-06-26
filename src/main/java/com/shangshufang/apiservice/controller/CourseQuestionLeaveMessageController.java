package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.CourseQuestionLeaveMessageServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course/answer")
public class CourseQuestionLeaveMessageController {
    @Autowired
    private CourseQuestionLeaveMessageServiceImpl serviceImpl;

    @RequestMapping(value = "/list/question/{questionID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("questionID") int questionID) {
        return serviceImpl.findList(questionID);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findList4Student(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findList4Student(pageNumber, pageSize, studentID);
    }
}
