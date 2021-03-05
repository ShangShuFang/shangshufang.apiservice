package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ResumeBrowsingHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResumeBrowsingHistoryMapper extends BaseMapper<ResumeBrowsingHistoryEntity> {
    int searchBrowseStudentTotalCount(@Param("companyID") int companyID);

    List<ResumeBrowsingHistoryEntity> searchBrowseStudentList(@Param("startIndex") int startIndex,
                                                              @Param("pageSize") int pageSize,
                                                              @Param("companyID") int companyID);

    int searchBrowsedByCompanyTotalCount(@Param("studentID") int studentID);

    List<ResumeBrowsingHistoryEntity> searchBrowsedByCompanyList(@Param("startIndex") int startIndex,
                                                                 @Param("pageSize") int pageSize,
                                                                 @Param("studentID") int studentID);
}
