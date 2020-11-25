package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CourseExercisesPaperDTO;
import com.shangshufang.apiservice.dto.CourseProgramExercisesMarkDTO;
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

    @RequestMapping(value = "/list/new/{pageNumber}/{pageSize}/{technologyID}/{universityCode}/{schoolID}/{courseID}/{studentID}/{studentName}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("universityCode") int universityCode,
                                    @PathVariable("schoolID") int schoolID,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("studentName") String studentName,
                                    @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findList(pageNumber, pageSize, technologyID, universityCode, schoolID, courseID, studentID, studentName, dataStatus);
    }

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{courseID}/{dataStatus}/{studentName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("dataStatus") String dataStatus,
                                    @PathVariable("studentName") String studentName) {
        return serviceImpl.findList(pageNumber, pageSize, courseID, dataStatus, studentName);
    }

    @RequestMapping(value = "/list/student/{pageNumber}/{pageSize}/{courseID}/{studentID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("courseID") int courseID,
                                    @PathVariable("studentID") int studentID,
                                    @PathVariable("dataStatus") String dataStatus) {
        return serviceImpl.findList4Student(pageNumber, pageSize, courseID, studentID, dataStatus);
    }

//    @RequestMapping(value = "/list/student/technology/{pageNumber}/{pageSize}/{universityCode}/{schoolID}/{studentID}/{technologyID}/{dataStatus}", method = RequestMethod.GET)
//    public UnifiedResponse findList4Technology(@PathVariable("pageNumber") int pageNumber,
//                                               @PathVariable("pageSize") int pageSize,
//                                               @PathVariable("universityCode") int universityCode,
//                                               @PathVariable("schoolID") int schoolID,
//                                               @PathVariable("studentID") int studentID,
//                                               @PathVariable("technologyID") int technologyID,
//                                               @PathVariable("dataStatus") String dataStatus) {
//        return serviceImpl.findList4Technology(pageNumber, pageSize, universityCode, schoolID, studentID, technologyID, dataStatus);
//    }

    @RequestMapping(value = "/any/{courseExercisesID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("courseExercisesID") int courseExercisesID) {
        return serviceImpl.findCourseExercisesDetail(courseExercisesID);
    }

    @RequestMapping(value = "/list/review/program/{courseExercisesID}/{courseExercisesDetailID}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("courseExercisesID") int courseExercisesID,
                                    @PathVariable("courseExercisesDetailID") int courseExercisesDetailID) {
        return serviceImpl.findProgramReviewList(courseExercisesID, courseExercisesDetailID);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public UnifiedResponse assign(@RequestBody UniversityStudentExercisesDTO dto) {
        return serviceImpl.assign(dto);
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public UnifiedResponse mark(@RequestBody CourseExercisesPaperDTO dto) {
        return serviceImpl.markCourseExercises(dto);
    }

    @RequestMapping(value = "/mark/program", method = RequestMethod.POST)
    public UnifiedResponse mark(@RequestBody CourseProgramExercisesMarkDTO dto) {
        return serviceImpl.correctProgramAnswer(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody UniversityStudentExercisesDTO dto) {
        return serviceImpl.change(dto);
    }
}
