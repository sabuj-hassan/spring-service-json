package com.sabuj.spring.api.service.controller;

import com.sabuj.spring.api.service.request.CreateCarRequest;
import com.sabuj.spring.api.service.request.DeleteCarRequest;
import com.sabuj.spring.api.service.request.GetCarRequest;
import com.sabuj.spring.api.service.request.UpdateCarRequest;
import com.sabuj.spring.api.service.response.CreateCarResponse;
import com.sabuj.spring.api.service.response.DeleteCarResponse;
import com.sabuj.spring.api.service.response.GetCarResponse;
import com.sabuj.spring.api.service.response.UpdateCarResponse;

/**
 * 
 * @author Sabuj
 */
public interface CarController {
    public GetCarResponse getAllCars();
    
    public CreateCarResponse createCar(CreateCarRequest request);
    
    public UpdateCarResponse updateCar(UpdateCarRequest request);
    
    public DeleteCarResponse deleteCar(DeleteCarRequest request);
    
    public GetCarResponse GetCar(GetCarRequest request);
}