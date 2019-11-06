package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ChinaRegionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChinaRegionMapper {
    List<ChinaRegionEntity> searchList(int regionParentCode);
}
