package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversityMapper extends BaseMapper<UniversityEntity> {
    int searchTotalCount(int provinceCode, int cityCode, String dataStatus);

    List<UniversityEntity> searchList(int startIndex, int pageSize, int provinceCode, int cityCode, String dataStatus);

    UniversityEntity searchByID(int universityCode);

    int checkUniversityCodeExist(String universityCode);

    int checkUniversityNameExist(String universityName);

    int updateBrand(UniversityEntity entity);

    int updateMemo(UniversityEntity entity);

    int delete(int universityID);
}
