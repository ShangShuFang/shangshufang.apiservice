package com.shangshufang.apiservice.manager;

import com.shangshufang.apiservice.constant.*;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnifiedResponseManager {
    private static Logger logger = LogManager.getLogger(UnifiedResponseManager.class);

    public static <T> UnifiedResponse buildSearchSuccessResponse(int totalCount, T data){
        return buildUnifiedResponse(ResponseCodeConstant.SUCCESS,
                ResponseMessageConstant.SUCCESS,
                ResponseResultConstant.SUCCESS,
                ResponseTypeConstant.SUCCESS,
                ResponseOptionConstant.NO,
                ResponseDataConstant.NO_AFFECTED,
                totalCount,
                data);
    }

    public static <T> UnifiedResponse buildLoginFailedResponse(int totalCount, T data){
        return buildUnifiedResponse(ResponseCodeConstant.LOGIN_FAILED,
                ResponseMessageConstant.LOGIN_FAILED,
                ResponseResultConstant.FAILED,
                ResponseTypeConstant.FAILED,
                ResponseOptionConstant.CUSTOMER,
                ResponseDataConstant.NO_AFFECTED,
                totalCount,
                data);
    }

    public static <T> UnifiedResponse buildSubmitSuccessResponse(int affectCount){
        return buildUnifiedResponse(ResponseCodeConstant.SUCCESS,
                ResponseMessageConstant.SUCCESS,
                ResponseResultConstant.SUCCESS,
                ResponseTypeConstant.SUCCESS,
                ResponseOptionConstant.NO,
                affectCount,
                ResponseDataConstant.NO_SEARCH_COUNT,
                ResponseDataConstant.NO_DATA);
    }

    public static <T> UnifiedResponse buildSubmitSuccessResponse(int affectCount, T data){
        return buildUnifiedResponse(ResponseCodeConstant.SUCCESS,
                ResponseMessageConstant.SUCCESS,
                ResponseResultConstant.SUCCESS,
                ResponseTypeConstant.SUCCESS,
                ResponseOptionConstant.NO,
                affectCount,
                ResponseDataConstant.NO_SEARCH_COUNT,
                data);
    }

    public static UnifiedResponse buildExceptionResponse(){
        return buildUnifiedResponse(ResponseCodeConstant.EXCEPTION,
                ResponseMessageConstant.EXCEPTION,
                ResponseResultConstant.EXCEPTION,
                ResponseTypeConstant.EXCEPTION,
                ResponseOptionConstant.ADMIN,
                ResponseDataConstant.NO_AFFECTED,
                ResponseDataConstant.NO_SEARCH_COUNT,
                ResponseDataConstant.NO_DATA);
    }

    public static UnifiedResponse buildExceptionResponse(String exMessage){
        return buildUnifiedResponse(ResponseCodeConstant.EXCEPTION,
                exMessage,
                ResponseResultConstant.EXCEPTION,
                ResponseTypeConstant.EXCEPTION,
                ResponseOptionConstant.ADMIN,
                ResponseDataConstant.NO_AFFECTED,
                ResponseDataConstant.NO_SEARCH_COUNT,
                ResponseDataConstant.NO_DATA);
    }

    private static <T> UnifiedResponse buildUnifiedResponse(String responseCode, String responseMessage, Boolean responseResult, String responseType, String responseOption, int affectCount, int totalCount, T data){
        UnifiedResponse<T> unifiedResponse = new UnifiedResponse<>();
        unifiedResponse.setResponseCode(responseCode);
        unifiedResponse.setResponseMessage(responseMessage);
        unifiedResponse.setResult(responseResult);
        unifiedResponse.setResponseType(responseType);
        unifiedResponse.setResponseOption(responseOption);
        unifiedResponse.setTotalCount(totalCount);
        unifiedResponse.setResponseData(data);
        return unifiedResponse;
    }
}
