package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ExercisesFileDTO;
import com.shangshufang.apiservice.entity.ExercisesDocumentEntity;
import com.shangshufang.apiservice.entity.ExercisesImageEntity;
import com.shangshufang.apiservice.entity.ExercisesKnowledgeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExercisesDocumentMapper;
import com.shangshufang.apiservice.mapper.ExercisesImageMapper;
import com.shangshufang.apiservice.service.ExercisesFileService;
import com.shangshufang.apiservice.vo.ExercisesDocumentVO;
import com.shangshufang.apiservice.vo.ExercisesFileVO;
import com.shangshufang.apiservice.vo.ExercisesImageVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercisesFileServiceImpl implements ExercisesFileService {
    @Autowired
    private ExercisesImageMapper exercisesImageMapper;
    @Autowired
    private ExercisesDocumentMapper exercisesDocumentMapper;

    private Logger logger = LogManager.getLogger(ExercisesFileServiceImpl.class);

    @Override
    public UnifiedResponse findList(int exercisesID) {
        try {
            ExercisesFileVO model = new ExercisesFileVO();
            List<ExercisesImageVO> imageModelList = new ArrayList<>();
            List<ExercisesDocumentVO> documentModelList = new ArrayList<>();

            List<ExercisesImageEntity> imageEntityList =  exercisesImageMapper.searchList(exercisesID);
            List<ExercisesDocumentEntity> imageDocumentList =  exercisesDocumentMapper.searchList(exercisesID);
            for (ExercisesImageEntity entity : imageEntityList) {
                ExercisesImageVO imageVO = new ExercisesImageVO();
                ObjectConvertUtils.toBean(entity, imageVO);
                imageModelList.add(imageVO);
            }
            for (ExercisesDocumentEntity entity : imageDocumentList) {
                ExercisesDocumentVO documentVO = new ExercisesDocumentVO();
                ObjectConvertUtils.toBean(entity, documentVO);
                documentModelList.add(documentVO);
            }

            model.setExercisesID(exercisesID);
            model.setImageList(imageModelList);
            model.setDocumentList(documentModelList);
            int totalCount = imageModelList.size() + documentModelList.size();
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(ExercisesFileDTO dto) {
        try{
            int affectRow = 0;
            //String[] imageList = dto.getImageList().split(",");
            //String[] documentList = dto.getDocumentList().split(",");
            List<ExercisesDocumentEntity> exercisesDocumentEntityList = JsonUtils.deserializationToObject(dto.getDocumentList(), ExercisesDocumentEntity.class);

            //更新图片信息
//            affectRow += exercisesImageMapper.delete(dto.getExercisesID());
//            for (String imageUrl : imageList) {
//                ExercisesImageEntity imageEntity = new ExercisesImageEntity();
//                imageEntity.setExercisesID(dto.getExercisesID());
//                imageEntity.setImageUrl(imageUrl);
//                imageEntity.setCreateUser(dto.getLoginUser());
//                imageEntity.setUpdateUser(dto.getLoginUser());
//                affectRow += exercisesImageMapper.insert(imageEntity);
//            }

            //更新文档信息
            affectRow += exercisesDocumentMapper.delete(dto.getExercisesID());
            for (ExercisesDocumentEntity documentEntity : exercisesDocumentEntityList) {
                documentEntity.setExercisesID(dto.getExercisesID());
                documentEntity.setCreateUser(dto.getLoginUser());
                documentEntity.setUpdateUser(dto.getLoginUser());
                affectRow += exercisesDocumentMapper.insert(documentEntity);
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        }catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
