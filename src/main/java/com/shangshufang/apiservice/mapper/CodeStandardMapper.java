package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.CodeStandardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CodeStandardMapper extends BaseMapper<CodeStandardEntity> {
    int searchTotalCount(@Param("languageID") int languageID);

    List<CodeStandardEntity> searchList(int startIndex, int pageSize, int languageID);

    List<CodeStandardEntity> searchListByTechnology(@Param("technologyID") int technologyID);

    int checkNameExist(int languageID, String codeStandardName);

    int delete(int languageID, int codeStandardID);
}
