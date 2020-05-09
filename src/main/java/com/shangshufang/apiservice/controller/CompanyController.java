package com.shangshufang.apiservice.controller;

import com.shangshufang.apiservice.dto.CompanyDTO;
import com.shangshufang.apiservice.dto.TechnologyKnowledgeUsingDTO;
import com.shangshufang.apiservice.dto.TechnologyUsingDTO;
import com.shangshufang.apiservice.service.impl.CompanyServiceImpl;
import com.shangshufang.apiservice.service.impl.TechnologyKnowledgeUsingServiceImpl;
import com.shangshufang.apiservice.service.impl.TechnologyUsingServiceImpl;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl serviceImpl;

    @Autowired
    private TechnologyKnowledgeUsingServiceImpl knowledgeUsingService;

    @Autowired
    private TechnologyUsingServiceImpl technologyUsingService;

    @RequestMapping(value = "/list/{pageNumber}/{pageSize}/{provinceCode}/{cityCode}/{dataStatus}", method = RequestMethod.GET)
    public UnifiedResponse findList(@PathVariable("pageNumber") int pageNumber,
                                    @PathVariable("pageSize") int pageSize,
                                    @PathVariable("provinceCode") int provinceCode,
                                    @PathVariable("cityCode") int cityCode,
                                    @PathVariable("dataStatus") String dataStatus){
        return serviceImpl.findList(pageNumber, pageSize, provinceCode, cityCode, dataStatus);
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public UnifiedResponse findList4Client(){
        return serviceImpl.findList4Client();
    }

    @RequestMapping(value = "/any/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse find(@PathVariable("companyID") int companyID){
        return serviceImpl.find(companyID);
    }

    @RequestMapping(value = "/check/name/{companyName}", method = RequestMethod.GET)
    public UnifiedResponse checkCompanyNameExist(@PathVariable("companyName") String companyName){
        return serviceImpl.checkCompanyNameExist(companyName);
    }

    @RequestMapping(value = "/check/cellphone/{cellphone}", method = RequestMethod.GET)
    public UnifiedResponse checkCellphoneExist(@PathVariable("cellphone") String cellphone){
        return serviceImpl.checkCellphoneExist(cellphone);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody CompanyDTO dto){
        return serviceImpl.add(dto);
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public UnifiedResponse change(@RequestBody CompanyDTO dto){
        return serviceImpl.change(dto);
    }

    @RequestMapping(value="/change/status", method = RequestMethod.PUT)
    public UnifiedResponse changeStatus(@RequestBody CompanyDTO dto){
        return serviceImpl.changeDataStatus(dto);
    }

    @RequestMapping(value="/change/logo", method = RequestMethod.PUT)
    public UnifiedResponse changeBrand(@RequestBody CompanyDTO dto){
        return serviceImpl.changeBrand(dto);
    }

    @RequestMapping(value="/change/memo", method = RequestMethod.PUT)
    public UnifiedResponse changeMemo(@RequestBody CompanyDTO dto){
        return serviceImpl.changeMemo(dto);
    }

    @RequestMapping(value="/change/recruit_level", method = RequestMethod.PUT)
    public UnifiedResponse changeRecruitLevel(@RequestBody CompanyDTO dto){
        return serviceImpl.changeRecruitLevel(dto);
    }

    @RequestMapping(value = "/delete/{companyID}", method = RequestMethod.DELETE)
    public UnifiedResponse delete(@PathVariable("companyID") int companyID){
        return serviceImpl.delete(companyID);
    }

    @RequestMapping(value = "/list/using/technology/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findUsingTechnologyList(@PathVariable("companyID") int companyID){
        return technologyUsingService.findUsingTechnologyList(companyID);
    }

    @RequestMapping(value = "/list/using/knowledge/{companyID}", method = RequestMethod.GET)
    public UnifiedResponse findUsingKnowledgeList(@PathVariable("companyID") int companyID){
        return knowledgeUsingService.findList(companyID);
    }

    @RequestMapping(value = "/list/using/learning_phase/{companyID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findLearningPhaseList(@PathVariable("companyID") int companyID, @PathVariable("technologyID") int technologyID){
        return knowledgeUsingService.findLearningPhaseList(companyID, technologyID);
    }

    @RequestMapping(value = "/list/using/knowledge/learning_phase/{companyID}/{technologyID}/{learningPhaseID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeList(@PathVariable("companyID") int companyID,
                                             @PathVariable("technologyID") int technologyID,
                                             @PathVariable("learningPhaseID") int learningPhaseID){
        return knowledgeUsingService.findKnowledgeList(companyID, technologyID, learningPhaseID);
    }

    @RequestMapping(value = "/list/using/knowledge/client/{companyID}/{technologyID}", method = RequestMethod.GET)
    public UnifiedResponse findKnowledgeList4Client(@PathVariable("companyID") int companyID, @PathVariable("technologyID") int technologyID){
        return knowledgeUsingService.findKnowledgeList4Client(companyID, technologyID);
    }

    @RequestMapping(value = "/add/using/technology", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyUsingDTO dto){
        return technologyUsingService.add(dto);
    }

    @RequestMapping(value = "/add/using/knowledge", method = RequestMethod.POST)
    public UnifiedResponse add(@RequestBody TechnologyKnowledgeUsingDTO dto){
        return knowledgeUsingService.add(dto);
    }


}
