package com.sabuj.spring.api.service.response;

import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.ServiceError;
import java.util.List;

/**
 * 
 * @author Sabuj
 */
public class GetCarResponse extends ServiceError{
    private List<Car> carList;
    private Integer total;
    
    public GetCarResponse(){
        super();
    }
    
    public GetCarResponse(com.sabuj.spring.api.business.entity.ServiceError serviceError){
        super(serviceError);
    }
    
    public GetCarResponse(Object []objectArray){
        if(objectArray.length == 2){
            this.carList = (List<Car>)objectArray[0];
            this.total = (Integer)objectArray[1];
        }
    }

    /**
     * @return the carList
     */
    public List<Car> getCarList() {
        return carList;
    }

    /**
     * @param carList the carList to set
     */
    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
