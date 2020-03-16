package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyCodeStandardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyCodeStandardMapper extends BaseMapper<TechnologyCodeStandardEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID);

    List<TechnologyCodeStandardEntity> searchList(int startIndex, int pageSize, int technologyID);

    int checkCodeStandardExist(int technologyID, String codeStandardName);

    int delete(int technologyID, int codeStandardID);
}
