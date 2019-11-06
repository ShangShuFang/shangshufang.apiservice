package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.SchoolEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchoolMapper extends BaseMapper<SchoolEntity> {
    int searchTotalCount(@Param("universityCode") int universityCode);

    List<SchoolEntity> searchList(int startIndex, int pageSize, int universityCode);

    SchoolEntity searchByID(int universityCode, int schoolID);

    int checkSchoolNameExist(int universityCode, String schoolName);

    int checkCellphoneExist(@Param("cellphone") String cellphone);

    int delete(int universityCode, int schoolID);
}
