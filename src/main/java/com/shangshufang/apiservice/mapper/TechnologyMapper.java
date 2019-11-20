package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologyMapper extends BaseMapper<TechnologyEntity> {
    int searchTotalCount();

    List<TechnologyEntity> searchList(int startIndex, int pageSize);

    TechnologyEntity searchByID(int technologyID);

    int checkTechnologyNameExist(String technologyName);

    int updateThumbnail(TechnologyEntity entity);

    int delete(int technologyID);
}
