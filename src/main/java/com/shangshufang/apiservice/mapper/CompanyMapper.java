package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<CompanyEntity> {
    int searchTotalCount(int provinceCode, int cityCode, String dataStatus);

    List<CompanyEntity> searchList(int startIndex, int pageSize, int provinceCode, int cityCode, String dataStatus);

    List<CompanyEntity> searchList4Client();

    CompanyEntity searchByID(int companyID);

    CompanyEntity searchByName(String companyName);

    int checkCellphoneExist(String cellphone);

    int updateBrand(CompanyEntity entity);

    int updateMemo(CompanyEntity entity);

    int delete(int companyID);
}
