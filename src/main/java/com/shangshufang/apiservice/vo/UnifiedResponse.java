package com.shangshufang.apiservice.vo;

public class UnifiedResponse<T> {
    private String responseCode;
    private String responseMessage;
    private String responseType;
    private boolean result;
    private String responseOption;
    private int totalCount;
    private int affectCount;
    private T responseData;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getResponseOption() {
        return responseOption;
    }

    public void setResponseOption(String responseOption) {
        this.responseOption = responseOption;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAffectCount() {
        return affectCount;
    }

    public void setAffectCount(int affectCount) {
        this.affectCount = affectCount;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}
