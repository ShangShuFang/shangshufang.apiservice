package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.service.impl.AnalysisAbilityServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analysis/ability/student")
public class AnalysisAbilityController {
    @Autowired
    private AnalysisAbilityServiceImpl serviceImpl;

    /**
     * 分析所有学生当前的专业能力
     *
     * @return 分析结果
     */
    @RequestMapping(value = "/analyse", method = RequestMethod.POST)
    public UnifiedResponse analyse() {
        return serviceImpl.analyse();
    }

    /**
     * 获取满足条件的学生能力分析结果信息列表
     *
     * @param pageNumber            页码
     * @param pageSize              每页数据
     * @param directionID           方向编号
     * @param categoryID            分类编号
     * @param technologyID          技术编号
     * @param studentUniversityCode 学生就读高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param teacherUniversityCode 教师所在高校编码
     * @param teacherSchoolID       教师所在二级学院编号
     * @param teacherID             教师编号
     * @param studentName           学生姓名
     * @return 学生能力分析结果信息列表
     */
    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{directionID}/{categoryID}/{technologyID}/{studentUniversityCode}/{studentSchoolID}/{teacherUniversityCode}/{teacherSchoolID}/{teacherID}/{studentName}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("directionID") int directionID,
                                    @PathVariable("categoryID") int categoryID,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("studentUniversityCode") int studentUniversityCode,
                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                    @PathVariable("teacherUniversityCode") int teacherUniversityCode,
                                    @PathVariable("teacherSchoolID") int teacherSchoolID,
                                    @PathVariable("teacherID") int teacherID,
                                    @PathVariable("studentName") String studentName) {
        return serviceImpl.findList(pageNumber, pageSize, directionID, categoryID, technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentName);
    }

    /**
     * 取得学生能力分析概要信息
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @return 学生能力分析概要信息
     */
    @RequestMapping(value = "/any/student_summary/{studentUniversityCode}/{studentSchoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findStudentSummaryResult(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                    @PathVariable("studentSchoolID") int studentSchoolID,
                                                    @PathVariable("studentID") int studentID) {
        return serviceImpl.findStudentSummaryResult(studentUniversityCode, studentSchoolID, studentID);
    }

    /**
     * 取得学生学习过的技术列表，包括学习情况概要信息（级别、站位、完成度）
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @return 指定学生学习过的技术列表
     */
    @RequestMapping(value = "/list/learning_technology/{studentUniversityCode}/{studentSchoolID}/{studentID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningTechnologyResultList(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                            @PathVariable("studentSchoolID") int studentSchoolID,
                                                            @PathVariable("studentID") int studentID) {
        return serviceImpl.findLearningTechnologyResultList(studentUniversityCode, studentSchoolID, studentID);
    }

    /**
     * 取得学生知识点掌握情况分析信息（已掌握、较薄弱、未掌握）
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 学生知识点掌握情况分析信息（已掌握、较薄弱、未掌握）
     */
    @RequestMapping(value = "/any/knowledge_summary/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeSummaryResult(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                      @PathVariable("studentSchoolID") int studentSchoolID,
                                                      @PathVariable("studentID") int studentID,
                                                      @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findKnowledgeSummaryResult(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }

    /**
     * 取得学生代码规范性分析信息
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param languageID            编程语言编号
     * @return 学生代码规范性分析信息
     */
    @RequestMapping(value = "/any/code_guideline/{studentUniversityCode}/{studentSchoolID}/{studentID}/{languageID}", method = RequestMethod.GET)
    public UnifiedResponse findCodeGuidelineResult(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                   @PathVariable("studentSchoolID") int studentSchoolID,
                                                   @PathVariable("studentID") int studentID,
                                                   @PathVariable("languageID") int languageID) {
        return serviceImpl.findCodeGuidelineResult(studentUniversityCode, studentSchoolID, studentID, languageID);
    }

    /**
     * 取得学生练习题完成数量分析信息
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 学生练习题完成数量分析信息
     */
    @RequestMapping(value = "/list/exercise_number/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findExerciseNumberResultList(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                        @PathVariable("studentSchoolID") int studentSchoolID,
                                                        @PathVariable("studentID") int studentID,
                                                        @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findExerciseNumberResultList(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }

    /**
     * 取得学生练习题完成百分比分析信息
     *
     * @param studentUniversityCode 学生所在高校编码
     * @param studentSchoolID       学生所在二级学院编号
     * @param studentID             学生编号
     * @param technologyID          技术编号
     * @return 学生练习题完成百分比分析信息
     */
    @RequestMapping(value = "/list/exercise_percent/{studentUniversityCode}/{studentSchoolID}/{studentID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findExercisePercentResultList(@PathVariable("studentUniversityCode") int studentUniversityCode,
                                                         @PathVariable("studentSchoolID") int studentSchoolID,
                                                         @PathVariable("studentID") int studentID,
                                                         @PathVariable("technologyID") int technologyID) {
        return serviceImpl.findExercisePercentResultList(studentUniversityCode, studentSchoolID, studentID, technologyID);
    }

}
