package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CompanyTalentPoolEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyTalentPoolMapper extends BaseMapper<CompanyTalentPoolEntity> {
    int searchTotalCount(@Param("companyID") int companyID,
                         @Param("technologyID") int technologyID,
                         @Param("dataStatus") String dataStatus);

    List<CompanyTalentPoolEntity> searchList(@Param("startIndex") int startIndex,
                                             @Param("pageSize") int pageSize,
                                             @Param("companyID") int companyID,
                                             @Param("technologyID") int technologyID,
                                             @Param("dataStatus") String dataStatus);

    CompanyTalentPoolEntity search(@Param("companyID") int companyID, @Param("studentID") int studentID);
}
