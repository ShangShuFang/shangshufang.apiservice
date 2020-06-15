package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.GrowingMapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrowingMapMapper extends BaseMapper<GrowingMapEntity> {
    int searchTotalCount();

    List<GrowingMapEntity> searchList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    GrowingMapEntity search(@Param("growingID") int growingID);

    int checkNameExist(@Param("growingTarget") String growingTarget);

    int delete(@Param("growingID") int growingID);
}
