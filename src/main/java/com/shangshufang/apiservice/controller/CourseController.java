package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.service.impl.CourseServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{teacherID}/{technologyID}/{courseTimeBegin}/{dataStatus}/{isSelf}/{searchType}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("teacherID") int teacherID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("courseTimeBegin") String courseTimeBegin,
                                    @PathVariable("dataStatus") String dataStatus,
                                    @PathVariable("isSelf") boolean isSelf,
                                    @PathVariable("searchType") String searchType) {
        return serviceImpl.findList(pageNumber, pageSize, universityCode, schoolID, teacherID, technologyID, courseTimeBegin, dataStatus, isSelf, searchType);
    }

    @RequestMapping(value = "/list/simple/{universityCode}/{schoolID}/{teacherID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findSimpleList(@PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("teacherID") int teacherID,
                                    @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findSimpleList(universityCode, schoolID, teacherID, technologyID);
    }

    @RequestMapping(value = "/any/{universityCode}/{schoolID}/{courseID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("universityCode") int universityCode,
                                @PathVariable("schoolID") int schoolID,
                                @PathVariable("courseID") int courseID,
                                @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.find(universityCode, schoolID, courseID, dataStatus);
    }

    @RequestMapping(value = "/check/course/{universityCode}/{schoolID}/{courseName}/{courseTimeBegin}/{courseTimeEnd}", method = RequestMethod.GET)
    public UnifiedResponse checkCourseExist(@PathVariable("universityCode") int universityCode,
                                            @PathVariable("schoolID") int schoolID,
                                            @PathVariable("courseName") String courseName,
                                            @PathVariable("courseTimeBegin") String courseTimeBegin,
                                            @PathVariable("courseTimeEnd") String courseTimeEnd) {
        return serviceImpl.checkCourseExist(universityCode, schoolID, courseName, courseTimeBegin, courseTimeEnd);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CourseDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value="/change", method = RequestMethod.PUT)
    public UnifiedResponse changeCourseBaseInfo(@RequestBody CourseDTO dto){
        return serviceImpl.changeCourseBaseInfo(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CourseDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/start", method = RequestMethod.PUT)
    public UnifiedResponse startCourse(){
        return serviceImpl.startCourse();
    }

}
