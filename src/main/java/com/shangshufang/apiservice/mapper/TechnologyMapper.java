package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyMapper extends BaseMapper<TechnologyEntity> {
    int searchTotalCount(int directionID, int categoryID, String dataStatus);

    List<TechnologyEntity> searchList(int startIndex, int pageSize, int directionID, int categoryID, String dataStatus);

    List<TechnologyEntity> searchList4Client(int startIndex, int pageSize, int directionID, int categoryID, String dataStatus);

    List<TechnologyEntity> searchSimpleList(int directionID, int categoryID, String dataStatus);

    List<TechnologyEntity> searchStudentLearning(int studentUniversityCode, int studentSchoolID, int studentID);

    TechnologyEntity searchSimple(@Param("technologyID") int technologyID);

    TechnologyEntity searchByID(int technologyID);

    int checkTechnologyNameExist(String technologyName);

    int updateThumbnail(TechnologyEntity entity);

    int delete(int technologyID);
}
