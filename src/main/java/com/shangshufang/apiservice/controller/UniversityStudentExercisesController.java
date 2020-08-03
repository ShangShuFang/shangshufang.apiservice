package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.service.impl.UniversityStudentExercisesServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course/exercises")
public class UniversityStudentExercisesController {
    @Autowired
    private UniversityStudentExercisesServiceImpl serviceImpl;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{courseUniversityCode}/{courseSchoolID}/{courseID}/{dataStatus}/{studentName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("courseUniversityCode") int courseUniversityCode,
                                    @PathVariable("courseSchoolID") int courseSchoolID,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("dataStatus") String dataStatus,
                                    @PathVariable("studentName") String studentName) {
        return serviceImpl.findList(pageNumber, pageSize, courseUniversityCode, courseSchoolID, courseID, dataStatus, studentName);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{studentID}/{courseID}/{dataStatus}/{studentName}/{isSelf}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("dataStatus") String dataStatus,
                                    @PathVariable("studentName") String studentName,
                                    @PathVariable("isSelf") boolean isSelf) {
        return serviceImpl.findList4Student(pageNumber, pageSize, studentID, courseID, dataStatus, studentName, isSelf);
    }

    @RequestMapping(value = "/list/student/technology/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{studentID}/{technologyID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList4Technology(@PathVariable("pageNumber") int pageNumber,
                                               @PathVariable("pageSize") int pageSize,
                                               @PathVariable("universityCode") int universityCode,
                                               @PathVariable("schoolID") int schoolID,
                                               @PathVariable("studentID") int studentID,
                                               @PathVariable("technologyID") int technologyID,
                                               @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findList4Technology(pageNumber, pageSize, universityCode, schoolID, studentID, technologyID, dataStatus);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public UnifiedResponse assign(@RequestBody UniversityStudentExercisesDTO dto) {
        return serviceImpl.assign(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityStudentExercisesDTO dto) {
        return serviceImpl.change(dto);
    }
}
