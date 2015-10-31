package com.sabuj.spring.api.service.response;

import com.sabuj.spring.api.business.entity.ServiceError;

/**
 * 
 * @author Sabuj
 */
public class DeleteCarResponse extends ServiceError{
    private Integer count;
    
    public DeleteCarResponse(){
        super();
    }
    
    public DeleteCarResponse(com.sabuj.spring.api.business.entity.ServiceError serviceError){
        super(serviceError);
    }
    
    public DeleteCarResponse(Integer count){
        this.count = count;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }    
}
