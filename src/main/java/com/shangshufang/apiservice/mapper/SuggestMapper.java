package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.SuggestEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestMapper extends BaseMapper<SuggestEntity> {
    int searchTotalCount(String portal, String cellphone, String dataStatus);

    List<SuggestEntity> searchList(int startIndex, int pageSize, String portal, String cellphone, String dataStatus);
}
