package com.sabuj.spring.api.database.manager;

import com.sabuj.spring.api.database.entity.Car;
import com.sabuj.spring.api.database.entity.CarAttribute;
import java.util.List;

/**
 * 
 * @author Sabuj
 */
public interface DatabaseManager {
    
    public List<Car> getAll();
    
    public Car saveCar(Car car);
    
    public CarAttribute saveCarAttribute(CarAttribute attribute);
    
    public Car updateCar(Car car);
    
    public Integer deleteCar(Integer carId);
    
    public Integer deleteCarAttributes(Integer carId);
    
    public Car getCarFindById(Integer carId);
    
    public Car getCarFindByRegistrationId(String registrationId);
    
    public List<Car> getCarsFindBySql(String sql);
}
