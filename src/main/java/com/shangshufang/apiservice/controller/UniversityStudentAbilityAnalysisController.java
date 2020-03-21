package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.UniversityStudentAbilityAnalysisServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student/ability")
public class UniversityStudentAbilityAnalysisController {
    @Autowired
    private UniversityStudentAbilityAnalysisServiceImpl serviceImpl;

    @RequestMapping(value = "/{pageNumber}/{pageSize}/{technologyID}/{studentUniversityCode}/{studentSchoolID}/{teacherUniversityCode}/{teacherSchoolID}/{teacherID}/{studentCellphone}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("studentUniversityCode") int studentUniversityCode,
                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                    @PathVariable("teacherUniversityCode") int teacherUniversityCode,
                                    @PathVariable("teacherSchoolID") int teacherSchoolID,
                                    @PathVariable("teacherID") int teacherID,
                                    @PathVariable("studentCellphone") String studentCellphone) {
        return serviceImpl.findList(pageNumber, pageSize, technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
    }

    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public UnifiedResponse analysis(){
        return serviceImpl.ability();
    }
}
