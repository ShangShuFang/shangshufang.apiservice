package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.DirectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DirectionMapper extends BaseMapper<DirectionEntity> {
    int searchTotalCount(@Param("dataStatus") String dataStatus);

    List<DirectionEntity> searchList(int startIndex, int pageSize, String dataStatus);

    DirectionEntity searchByID(int directionID);

    int checkDirectionNameExist(String directionName);

    int updateThumbnail(DirectionEntity entity);

    int delete(int directionID);
}
