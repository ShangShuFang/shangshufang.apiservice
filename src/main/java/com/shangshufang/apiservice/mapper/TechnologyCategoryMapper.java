package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyCategoryMapper extends BaseMapper<TechnologyCategoryEntity> {
    int searchTotalCount(int directionID, String dataStatus);

    List<TechnologyCategoryEntity> searchList(int startIndex, int pageSize, int directionID, String dataStatus);

    int checkNameExist(int directionID, String categoryName);

    int delete(int directionID, int technologyCategoryID);
}
