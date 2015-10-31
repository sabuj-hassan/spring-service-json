package com.sabuj.spring.api.service.request;

/**
 * 
 * @author Sabuj
 */
public class DeleteCarRequest{
    private Integer carId;
    
    public DeleteCarRequest(){
        super();
    }

    /**
     * @return the carId
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    
}
