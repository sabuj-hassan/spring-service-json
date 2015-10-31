package com.sabuj.spring.api.business.entity;

/**
 * 
 * @author Sabuj
 */
public class ServiceError {
    private Integer errorCode;
    private String errorMsg;

    public ServiceError(){
        
    }
    
    public ServiceError(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        
        if(errorCode.intValue() == Utils.ERROR_NULL_VALUE){
            this.errorMsg = "Provided value is null: "+errorMsg;
        }
        else if(errorCode.intValue() == Utils.ERROR_VALUE_INVALID){
            this.errorMsg = "No record found for : "+errorMsg;
        }
        else if(errorCode.intValue() == Utils.ERROR_VALUE_MISSING){
            this.errorMsg = "Required parameter is missing : "+errorMsg;
        }
        else if(errorCode.intValue() == Utils.ERROR_VALUE_DUPLICATE){
            this.errorMsg = "Data already exist for : "+errorMsg;
        }
        else{
            this.errorMsg = errorMsg;
        }
    }
    
    public ServiceError(Integer errorCode){
        this.errorCode = errorCode;
        if(errorCode.intValue() == Utils.ERROR_INVALID_JSON){
            this.errorMsg = "Invalid JSON object found in POST request";
        }
    }
    
    public ServiceError(ServiceError serviceError){
        if(serviceError != null){
            this.errorCode = serviceError.getErrorCode();
            this.errorMsg = serviceError.getErrorMsg();
        }
    }
    
    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
