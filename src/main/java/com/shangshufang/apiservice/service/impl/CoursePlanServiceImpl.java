package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CoursePlanStatus;
import com.shangshufang.apiservice.constant.CourseStatus;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CoursePlanDTO;
import com.shangshufang.apiservice.entity.CoursePlanEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CoursePlanMapper;
import com.shangshufang.apiservice.service.CoursePlanService;
import com.shangshufang.apiservice.vo.CoursePlanVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {
    @Autowired
    private CoursePlanMapper myMapper;
    private Logger logger = LogManager.getLogger(CoursePlanServiceImpl.class);

    @Override
    public UnifiedResponse finishCourseClass(CoursePlanDTO dto) {
        try {
            CoursePlanEntity entity = new CoursePlanEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setDataStatus(CoursePlanStatus.Finished);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int universityCode, int schoolID, int courseID) {
        try {
            List<CoursePlanVO> modelList = new ArrayList<>();

            List<CoursePlanEntity> entityList = myMapper.searchAll(universityCode, schoolID, courseID);
            if(entityList.isEmpty()){
                return null;
            }
            for (CoursePlanEntity entity : entityList ) {
                CoursePlanVO model = new CoursePlanVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
