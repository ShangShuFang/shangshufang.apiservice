package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ResumeBrowsingHistoryDTO;
import com.shangshufang.apiservice.entity.ResumeBrowsingHistoryEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ResumeBrowsingHistoryMapper;
import com.shangshufang.apiservice.service.ResumeBrowsingHistoryService;
import com.shangshufang.apiservice.vo.ResumeBrowsingHistoryVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeBrowsingHistoryServiceImpl implements ResumeBrowsingHistoryService {
    @Autowired
    private ResumeBrowsingHistoryMapper myMapper;
    private final Logger logger = LogManager.getLogger(ResumeBrowsingHistoryServiceImpl.class);

    @Override
    public UnifiedResponse findBrowseStudentTotalCount(int companyID) {
        try {
            int totalCount = myMapper.searchBrowseStudentTotalCount(companyID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, totalCount);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findBrowsedByCompanyTotalCount(int studentID) {
        try {
            int totalCount = myMapper.searchBrowsedByCompanyTotalCount(studentID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, totalCount);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findBrowseStudentList(int pageNumber, int pageSize, int companyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<ResumeBrowsingHistoryVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchBrowseStudentTotalCount(companyID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ResumeBrowsingHistoryEntity> entityList = myMapper.searchBrowseStudentList(startIndex, pageSize, companyID);
            for (ResumeBrowsingHistoryEntity entity : entityList) {
                ResumeBrowsingHistoryVO model = new ResumeBrowsingHistoryVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findBrowsedByCompanyList(int pageNumber, int pageSize, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<ResumeBrowsingHistoryVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchBrowsedByCompanyTotalCount(studentID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ResumeBrowsingHistoryEntity> entityList = myMapper.searchBrowsedByCompanyList(startIndex, pageSize, studentID);
            for (ResumeBrowsingHistoryEntity entity : entityList) {
                ResumeBrowsingHistoryVO model = new ResumeBrowsingHistoryVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(ResumeBrowsingHistoryDTO dto) {
        try {
            ResumeBrowsingHistoryEntity entity = new ResumeBrowsingHistoryEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ResumeBrowsingHistoryDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(ResumeBrowsingHistoryDTO dto) {
        return null;
    }
}
