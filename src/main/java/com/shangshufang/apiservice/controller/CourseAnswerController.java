package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseQuestionLeaveMessageDTO;
import com.shangshufang.apiservice.service.impl.CourseQuestionLeaveMessageServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course/answer")
public class CourseAnswerController {
    @Autowired
    private CourseQuestionLeaveMessageServiceImpl serviceImpl;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseQuestionLeaveMessageDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CourseQuestionLeaveMessageDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
