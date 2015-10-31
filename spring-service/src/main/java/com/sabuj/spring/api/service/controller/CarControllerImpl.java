package com.sabuj.spring.api.service.controller;
import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.ServiceError;
import com.sabuj.spring.api.business.entity.Utils;
import com.sabuj.spring.api.service.request.CreateCarRequest;
import com.sabuj.spring.api.service.request.DeleteCarRequest;
import com.sabuj.spring.api.service.request.GetCarRequest;
import com.sabuj.spring.api.service.request.UpdateCarRequest;
import com.sabuj.spring.api.service.response.CreateCarResponse;
import com.sabuj.spring.api.service.response.DeleteCarResponse;
import com.sabuj.spring.api.service.response.UpdateCarResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.sabuj.spring.api.business.manager.CarManager;
import com.sabuj.spring.api.service.response.GetCarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Sabuj
 */
@RestController
public class CarControllerImpl implements CarController{
    
    @Autowired
    private CarManager carManager;
    
    @RequestMapping(value = "/getcars.json", method = RequestMethod.GET)
    public @ResponseBody GetCarResponse getAllCars() {
        Object object = carManager.getAllCars();
        if(object instanceof com.sabuj.spring.api.business.entity.ServiceError){
            return new GetCarResponse((com.sabuj.spring.api.business.entity.ServiceError)object);
        }
        return new GetCarResponse((Object[])object);
    }

    @RequestMapping(value = "/createcar.json", method = RequestMethod.POST)
    public @ResponseBody CreateCarResponse createCar(@RequestBody CreateCarRequest request) {
        if(request == null || request.getCar() == null){
            return new CreateCarResponse(new ServiceError(Utils.ERROR_INVALID_JSON));
        }
        Object object = carManager.createCar(request.getCar());
        if(object instanceof com.sabuj.spring.api.business.entity.ServiceError){
            return new CreateCarResponse((com.sabuj.spring.api.business.entity.ServiceError)object);
        }
        return new CreateCarResponse((Car)object);
    }

    @RequestMapping(value = "/updatecar.json", method = RequestMethod.POST)
    public @ResponseBody UpdateCarResponse updateCar(@RequestBody UpdateCarRequest request) {
        if(request == null || request.getCar() == null){
            return new UpdateCarResponse(new ServiceError(Utils.ERROR_INVALID_JSON));
        }
        Object object = carManager.updateCar(request.getCar());
        if(object instanceof com.sabuj.spring.api.business.entity.ServiceError){
            return new UpdateCarResponse((com.sabuj.spring.api.business.entity.ServiceError)object);
        }
        return new UpdateCarResponse((Car)object);
    }

    @RequestMapping(value = "/deletecar.json", method = RequestMethod.POST)
    public @ResponseBody DeleteCarResponse deleteCar(@RequestBody DeleteCarRequest request) {
        if(request == null || request.getCarId() == null){
            return new DeleteCarResponse(new ServiceError(Utils.ERROR_INVALID_JSON));
        }
        Object object = carManager.deleteCar(request.getCarId());
        if(object instanceof com.sabuj.spring.api.business.entity.ServiceError){
            return new DeleteCarResponse((com.sabuj.spring.api.business.entity.ServiceError)object);
        }
        return new DeleteCarResponse((Integer)object);
    }

    @RequestMapping(value = "/getcar.json", method = RequestMethod.POST)
    public @ResponseBody GetCarResponse GetCar(@RequestBody GetCarRequest request) {
        if(request == null || request.getCriteria() == null){
            return new GetCarResponse(new ServiceError(Utils.ERROR_INVALID_JSON));
        }
        Object object = carManager.getCar(request.getCriteria());
        if(object instanceof com.sabuj.spring.api.business.entity.ServiceError){
            return new GetCarResponse((com.sabuj.spring.api.business.entity.ServiceError)object);
        }
        return new GetCarResponse((Object[])object);
    }
}