package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.BusinessAnalyseLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessAnalyseLogMapper extends BaseMapper<BusinessAnalyseLogEntity> {
    int searchTotalCount();

    List<BusinessAnalyseLogEntity> searchList(int startIndex, int pageSize);
}
