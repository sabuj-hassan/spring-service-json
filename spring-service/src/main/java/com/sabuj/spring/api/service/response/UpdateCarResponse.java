package com.sabuj.spring.api.service.response;

import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.ServiceError;

/**
 * 
 * @author Sabuj
 */
public class UpdateCarResponse extends ServiceError{
    private Car car;
    
    public UpdateCarResponse(){
        super();
    }
    
    public UpdateCarResponse(com.sabuj.spring.api.business.entity.ServiceError serviceError){
        super(serviceError);
    }
    
    public UpdateCarResponse(Car car){
        this.car = car;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
}
