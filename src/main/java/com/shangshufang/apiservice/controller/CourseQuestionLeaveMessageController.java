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
@RequestMapping("/api/course/question/leaveMessage")
public class CourseQuestionLeaveMessageController {
    @Autowired
    private CourseQuestionLeaveMessageServiceImpl serviceImpl;

    @RequestMapping(method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseQuestionLeaveMessageDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/changeStatus", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CourseQuestionLeaveMessageDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }
}
