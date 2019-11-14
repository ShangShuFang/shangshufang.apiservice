package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyCustomerEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyCustomerMapper extends BaseMapper<CompanyCustomerEntity> {
    CompanyCustomerEntity search(int customerID, String cellphone);

    int checkCellphoneExist(String cellphone);

    int checkEmailExist(String email);

    int updateCellphone(CompanyCustomerEntity entity);

    int delete(int companyID, int customerID);
}
