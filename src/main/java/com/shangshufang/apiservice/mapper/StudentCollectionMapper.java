package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.StudentCollectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentCollectionMapper {
    int searchTotalCount(@Param("studentID") int studentID);

    List<StudentCollectionEntity> searchList(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("studentID") int studentID);

    int checkCollected(@Param("studentID") int studentID, @Param("companyID") int companyID);

    int insert(StudentCollectionEntity entity);

    int delete(@Param("studentID") int studentID, @Param("companyID") int companyID);
}
