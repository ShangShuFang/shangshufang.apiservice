package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityStudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityStudentMapper extends BaseMapper<UniversityStudentEntity> {
    int searchTotalCount(int universityCode, int schoolID, String fullName);

    List<UniversityStudentEntity> searchList(@Param("startIndex") int startIndex,
                                             @Param("pageSize") int pageSize,
                                             @Param("universityCode") int universityCode,
                                             @Param("schoolID") int schoolID,
                                             @Param("majorID") int majorID,
                                             @Param("fullName") String fullName);

    UniversityStudentEntity search(int universityCode, int schoolID, int studentID);

    UniversityStudentEntity login(String cellphone, String password);

    int checkCellphoneExist(String cellphone);

    int checkEmailExist(String email);

    int updatePassword(UniversityStudentEntity entity);

    int resetPassword(UniversityStudentEntity entity);
}
