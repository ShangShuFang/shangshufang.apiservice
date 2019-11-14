package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyAccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyAccountMapper extends BaseMapper<CompanyAccountEntity> {
    int searchTotalCount(@Param("companyID") int companyID);

    List<CompanyAccountEntity> searchList(int startIndex, int pageSize, int companyID);

    CompanyAccountEntity login(String cellphone, String password);

    int checkCellphoneExist(String cellphone);

    int delete(int companyID, int accountID);
}
