package com.sabuj.spring.api.service.response;

import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.ServiceError;

/**
 * 
 * @author Sabuj
 */
public class CreateCarResponse extends ServiceError{
    private Car car;
    
    public CreateCarResponse(){
        super();
    }
    
    public CreateCarResponse(com.sabuj.spring.api.business.entity.ServiceError serviceError){
        super(serviceError);
    }
    
    public CreateCarResponse(Car car){
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
