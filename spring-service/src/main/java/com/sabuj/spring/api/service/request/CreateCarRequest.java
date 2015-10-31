package com.sabuj.spring.api.service.request;

import com.sabuj.spring.api.business.entity.Car;

/**
 * 
 * @author Sabuj
 */
public class CreateCarRequest{
    private Car car;
    
    public CreateCarRequest(){
        super();
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
