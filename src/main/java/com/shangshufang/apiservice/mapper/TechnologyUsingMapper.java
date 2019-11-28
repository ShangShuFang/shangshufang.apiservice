package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyUsingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologyUsingMapper extends BaseMapper<TechnologyUsingEntity> {
    List<TechnologyUsingEntity> searchUsingTechnologyList(int companyID);

    int delete(int companyID);
}
