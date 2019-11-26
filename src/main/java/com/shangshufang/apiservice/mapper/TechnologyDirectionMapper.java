package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyDirectionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologyDirectionMapper extends BaseMapper<TechnologyDirectionEntity>{
    List<TechnologyDirectionEntity> searchList4Technology(int technologyID);

    List<TechnologyDirectionEntity> searchList4Direction(int directionID);

    int delete(int technologyID);
}
