package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.StudentCollectionDTO;
import com.shangshufang.apiservice.entity.StudentCollectionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.StudentCollectionMapper;
import com.shangshufang.apiservice.service.StudentCollectionService;
import com.shangshufang.apiservice.vo.StudentCollectionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCollectionServiceImpl implements StudentCollectionService {
    @Autowired
    private StudentCollectionMapper myMapper;
    private final Logger logger = LogManager.getLogger(StudentCollectionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentCollectionVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(studentID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentCollectionEntity> entityList =  myMapper.searchList(startIndex, pageSize, studentID);
            for (StudentCollectionEntity entity : entityList) {
                StudentCollectionVO model = new StudentCollectionVO();
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
    public UnifiedResponse checkCollected(int studentID, int companyID) {
        try {
            int totalCount = myMapper.checkCollected(studentID, companyID);
            boolean isCollection = totalCount > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, isCollection);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(StudentCollectionDTO dto) {
        try {
            StudentCollectionEntity entity = new StudentCollectionEntity();
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
    public UnifiedResponse delete(int studentID, int companyID) {
        try {
            int affectRow = myMapper.delete(studentID, companyID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
