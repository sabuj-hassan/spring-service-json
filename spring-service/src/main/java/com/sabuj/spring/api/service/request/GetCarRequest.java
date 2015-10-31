package com.sabuj.spring.api.service.request;

import com.sabuj.spring.api.business.entity.CarSearchCriteria;

/**
 * 
 * @author Sabuj
 */
public class GetCarRequest{
    private CarSearchCriteria criteria;
    
    public GetCarRequest(){
        super();
    }

    /**
     * @return the criteria
     */
    public CarSearchCriteria getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(CarSearchCriteria criteria) {
        this.criteria = criteria;
    }

    
}
