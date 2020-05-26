package com.shangshufang.apiservice.vo;

import lombok.Data;

@Data
public class UniversityStudentExercisesVO extends BaseVO {
    private int studentExercisesID;

    private int studentUniversityCode;
    private String studentUniversityName;
    private int studentSchoolID;
    private String studentSchoolName;
    private int studentID;
    private String studentName;
    private String studentPhoto;
    private String cellphone;
    private String enrollmentYear;
    private float positionSite;
    private String abilityLevel;
//    private int assistant;

    private int courseUniversityCode;
    private String courseUniversityName;
    private int courseSchoolID;
    private String courseSchoolName;
    private int courseID;
    private String courseName;
    private int courseClass;

    private int technologyID;
    private String technologyName;
    private int learningPhaseID;
    private String learningPhaseName;
    private int knowledgeID;
    private String knowledgeName;
    private int exercisesDocumentID;
    private String exercisesDocumentUrl;
    private String exercisesAnswerUrl;


    private String sourceCodeGitUrl;

    private int reviewerID;
    private String reviewerNameTeacher;
    private String reviewerNameStudent;
    private String reviewerType;
    private String reviewTime;
}
