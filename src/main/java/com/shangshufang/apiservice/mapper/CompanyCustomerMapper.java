package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyCustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CompanyCustomerMapper extends BaseMapper<CompanyCustomerEntity> {
    CompanyCustomerEntity search(int customerID, String cellphone);

    int checkCellphoneExist(@Param("cellphone") String cellphone);

    int checkEmailExist(String email);

    int updateCellphone(CompanyCustomerEntity entity);

    int updatePassword(CompanyCustomerEntity entity);

    int delete(int companyID, int customerID);
}
