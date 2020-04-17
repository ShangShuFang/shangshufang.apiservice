package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.MajorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper extends BaseMapper<MajorEntity> {
    int searchTotalCount(int universityCode, int schoolID);

    List<MajorEntity> searchList(int startIndex, int pageSize, int universityCode, int schoolID);

    int checkNameExist(int universityCode, int schoolID, String majorName);

    int delete(int universityCode, int schoolID, int majorID);
}
