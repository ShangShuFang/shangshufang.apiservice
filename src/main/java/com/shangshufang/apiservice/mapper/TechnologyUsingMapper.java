package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyUsingEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyUsingMapper extends BaseMapper<TechnologyUsingEntity> {
    List<TechnologyUsingEntity> searchUsingTechnologyList(@Param("companyID") int companyID);

    int searchCompanyTotalCount(@Param("technologyID") int technologyID);

    List<TechnologyUsingEntity> searchCompanyList(@Param("startIndex") int startIndex,
                                                  @Param("pageSize") int pageSize,
                                                  @Param("technologyID") int technologyID);

    String searchLowestRecruitLevel(@Param("technologyID") int technologyID);

    int delete(int companyID);
}
