package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.ExerciseWarehouseKnowledgeChoiceQuestionDTO;
import com.shangshufang.apiservice.service.impl.ExerciseWarehouseKnowledgeChoiceQuestionServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercise/knowledge/choice")
public class ExerciseWarehouseKnowledgeChoiceQuestionController {
    @Autowired
    private ExerciseWarehouseKnowledgeChoiceQuestionServiceImpl myService;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{technologyID}/{knowledgeID}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("technologyID") int technologyID,
                                    @PathVariable("knowledgeID") int knowledgeID,
                                    @PathVariable("dataStatus") String dataStatus){
        return myService.findList(pageNumber, pageSize, technologyID, knowledgeID, dataStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody ExerciseWarehouseKnowledgeChoiceQuestionDTO dto){
        return myService.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody ExerciseWarehouseKnowledgeChoiceQuestionDTO dto){
        return myService.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody ExerciseWarehouseKnowledgeChoiceQuestionDTO dto){
        return myService.changeDataStatus(dto);
    }

    @RequestMapping(value = "/delete/{technologyID}/{knowledgeID}/{exercisesID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("technologyID") int technologyID,
                                  @PathVariable("knowledgeID") int knowledgeID,
                                  @PathVariable("exercisesID") int exercisesID){
        return myService.delete(technologyID, knowledgeID, exercisesID);
    }
}
