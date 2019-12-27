package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityAccountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityAccountMapper extends BaseMapper<UniversityAccountEntity> {
    int searchTotalCount(int universityCode, int schoolID);

    List<UniversityAccountEntity> searchList(int startIndex, int pageSize, int universityCode, int schoolID);

    UniversityAccountEntity teacherLogin(String cellphone, String password);

    UniversityAccountEntity studentLogin(String cellphone, String password);

    int checkCellphoneExist(String cellphone);

    int checkEmailExist(String email);

    int delete(int universityCode, int schoolID, int accountID);

    int deleteByCellphone(String cellphone);
}
