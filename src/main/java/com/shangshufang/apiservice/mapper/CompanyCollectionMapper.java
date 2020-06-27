package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyCollectionEntity;
import com.shangshufang.apiservice.entity.StudentCollectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyCollectionMapper {
    int searchCompanyTotalCount(@Param("studentID") int studentID);

    List<CompanyCollectionEntity> searchCompanyList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("studentID") int studentID);

    int checkCollected(@Param("companyID") int companyID, @Param("studentID") int studentID);

    int insert(CompanyCollectionEntity entity);

    int delete(@Param("companyID") int companyID, @Param("studentID") int studentID);
}
