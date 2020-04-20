package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityAccountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityAccountMapper extends BaseMapper<UniversityAccountEntity> {
    int searchTotalCount(int universityCode, int schoolID);

    List<UniversityAccountEntity> searchList(int startIndex, int pageSize, int universityCode, int schoolID);

    int searchWaitApproveTotalCount4Client(int universityCode, int schoolID, int teacherID);

    int searchTotalCount4Client(int universityCode, int schoolID, int teacherID, String dataStatus);

    List<UniversityAccountEntity> searchList4Client(int startIndex, int pageSize, int universityCode, int schoolID, int accountID, String dataStatus);

    UniversityAccountEntity teacherLogin(String cellphone, String password);

    UniversityAccountEntity studentLogin(String cellphone, String password);

    int checkCellphone4Register(String cellphone);

    int checkCellphone4ChangePassword(String cellphone);

    int checkEmailExist(String email);

    int updatePassword(UniversityAccountEntity entity);

    int delete(int universityCode, int schoolID, int accountID);

    int deleteByCellphone(String cellphone);
}
