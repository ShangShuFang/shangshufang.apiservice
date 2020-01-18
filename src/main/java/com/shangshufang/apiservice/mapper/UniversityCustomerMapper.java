package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityCustomerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityCustomerMapper extends BaseMapper<UniversityCustomerEntity> {
    List<UniversityCustomerEntity> searchList(int universityCode, int schoolID, String customerRole, String fullName);

    UniversityCustomerEntity search(int customerID, String cellphone);

    int checkCellphoneExist(String cellphone);

    int checkEmailExist(String email);

    int updateCellphone(UniversityCustomerEntity entity);

    int delete(int universityCode, int schoolID, int customerID);

    int deleteByCellphone(String cellphone);
}
