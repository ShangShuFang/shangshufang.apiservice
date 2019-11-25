package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.DirectionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirectionMapper extends BaseMapper<DirectionEntity> {
    int searchTotalCount();

    List<DirectionEntity> searchList(int startIndex, int pageSize);

    DirectionEntity searchByID(int directionID);

    int checkDirectionNameExist(String directionName);

    int updateThumbnail(DirectionEntity entity);

    int delete(int directionID);
}
