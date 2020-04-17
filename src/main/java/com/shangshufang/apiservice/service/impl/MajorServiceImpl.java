package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.MajorDTO;
import com.shangshufang.apiservice.entity.MajorEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.MajorMapper;
import com.shangshufang.apiservice.service.MajorService;
import com.shangshufang.apiservice.vo.MajorVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper myMapper;

    private Logger logger = LogManager.getLogger(MajorServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<MajorVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchTotalCount(universityCode, schoolID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<MajorEntity> entityList = myMapper.searchList(startIndex, pageSize, universityCode, schoolID);
            for (MajorEntity entity : entityList) {
                MajorVO model = new MajorVO();
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
    public UnifiedResponse checkNameExist(int universityCode, int schoolID, String majorName) {
        try {
            int count = myMapper.checkNameExist(universityCode, schoolID, majorName);
            Boolean exist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int universityCode, int schoolID, int majorID) {
        try {
            int affectRow = myMapper.delete(universityCode, schoolID, majorID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(MajorDTO dto) {
        try {
            MajorEntity entity = new MajorEntity();
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
    public UnifiedResponse change(MajorDTO dto) {
        try {
            MajorEntity entity = new MajorEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(MajorDTO dto) {
        return null;
    }
}
