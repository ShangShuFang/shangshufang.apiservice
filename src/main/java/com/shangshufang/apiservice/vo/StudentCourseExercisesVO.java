package com.shangshufang.apiservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class StudentCourseExercisesVO extends BaseVO {
    private int courseExercisesID;
    private int courseID;
    private String courseName;
    private int courseClass;
    private int studentID;
    private String studentName;
    private List<StudentCourseExercisesDetailVO> choiceExercisesList;
    private List<StudentCourseExercisesDetailVO> blankExercisesList;
    private List<StudentCourseExercisesDetailVO> programExercisesList;
}
