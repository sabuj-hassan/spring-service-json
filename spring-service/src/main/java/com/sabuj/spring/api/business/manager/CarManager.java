package com.sabuj.spring.api.business.manager;

import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.CarSearchCriteria;

/**
 * 
 * @author Sabuj
 */
public interface CarManager {
    
    public Object getAllCars();
    
    public Object createCar(Car car);
    
    public Object updateCar(Car car);
    
    public Object deleteCar(Integer carId);
    
    public Object getCar(CarSearchCriteria criteria);
}
