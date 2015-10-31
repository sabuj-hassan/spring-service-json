package com.sabuj.spring.api.business.manager;

import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.business.entity.CarAttribute;
import com.sabuj.spring.api.business.entity.CarSearchCriteria;
import com.sabuj.spring.api.business.entity.Utils;
import com.sabuj.spring.api.business.entity.ServiceError;
import com.sabuj.spring.api.database.manager.DatabaseManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Sabuj
 */
@Service
public class CarManagerImpl implements CarManager{
    @Autowired
    private DatabaseManager databaseManager;
    
    public CarManagerImpl(){
        
    }
    
    public Object getAllCars(){
        List<com.sabuj.spring.api.database.entity.Car> carListEO = databaseManager.getAll();
        List<Car> carListBO = new ArrayList<Car>();
        if(carListEO != null){
            for(com.sabuj.spring.api.database.entity.Car carEO : carListEO){
                carListBO.add(new Car(carEO));
            }
        }
        Integer total = carListBO.size();
        Object []objectArray = new Object[]{carListBO, total};
        return objectArray;
    }

    public Object createCar(Car car) {
        if(car == null){
            return new ServiceError(Utils.ERROR_NULL_VALUE, "car object");
        }
        if(Utils.isEmpty(car.getRegistrationId())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's registration id");
        }
        if(Utils.isEmpty(car.getMake())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's make");
        }
        if(Utils.isEmpty(car.getModel())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's model");
        }
        if(car.getYear() == null){
            return new ServiceError(Utils.ERROR_NULL_VALUE, "car's year");
        }
        if(Utils.isEmpty(car.getColor())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's color");
        }
        if(Utils.isEmpty(car.getDescription())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's description");
        }
        
        com.sabuj.spring.api.database.entity.Car carEO = databaseManager.getCarFindByRegistrationId(car.getRegistrationId());
        if(carEO != null){
            return new ServiceError(Utils.ERROR_VALUE_DUPLICATE, "car's registrationId="+car.getRegistrationId());
        }
        
        carEO = new com.sabuj.spring.api.database.entity.Car();
        carEO.setRegistrationId(car.getRegistrationId());
        carEO.setMake(car.getMake());
        carEO.setModel(car.getModel());
        carEO.setYear(Utils.getStringToDate(car.getYear()));
        carEO.setColor(car.getColor());
        carEO.setDescription(car.getDescription());
        
        carEO = databaseManager.saveCar(carEO);
        System.out.println("Car added to table with id: "+carEO.getCarId());
        
        List<com.sabuj.spring.api.database.entity.CarAttribute> attributeListEO = saveAttributes(carEO.getCarId(), car.getCarAttributeList());
        carEO.setCarAttributeList(attributeListEO);
        return new Car(carEO);
    }
    
    private List<com.sabuj.spring.api.database.entity.CarAttribute> saveAttributes(Integer carId, List<CarAttribute> carAttributeList){
        List<com.sabuj.spring.api.database.entity.CarAttribute> attributeListEO = new ArrayList<com.sabuj.spring.api.database.entity.CarAttribute>();
        if(carAttributeList != null){
            for(CarAttribute attributeBO : carAttributeList){
                if(attributeBO == null || Utils.isEmpty(attributeBO.getKey()) || Utils.isEmpty(attributeBO.getValue())){
                    continue;
                }
                com.sabuj.spring.api.database.entity.CarAttribute attributeEO = new com.sabuj.spring.api.database.entity.CarAttribute();
                
                attributeEO.setCarId(carId);
                attributeEO.setKey(attributeBO.getKey());
                attributeEO.setValue(attributeBO.getValue());
                
                attributeEO = databaseManager.saveCarAttribute(attributeEO);
                System.out.println("Attribute saved with id: "+attributeEO.getAttributeId());
                attributeListEO.add(attributeEO);
            }
        }
        return attributeListEO;
    }

    public Object updateCar(Car car) {
        if(car == null){
            return new ServiceError(Utils.ERROR_NULL_VALUE, "car object");
        }
        if(Utils.isEmpty(car.getCarId())){
            return new ServiceError(Utils.ERROR_VALUE_MISSING, "car's carId");
        }
        
        com.sabuj.spring.api.database.entity.Car carEO = databaseManager.getCarFindById(car.getCarId());
        if(carEO == null){
            return new ServiceError(Utils.ERROR_VALUE_INVALID, "car's carId="+car.getCarId());
        }
        System.out.println("Found card record from DB for id: "+car.getCarId());
        
        if(!Utils.isEmpty(car.getRegistrationId())){
            com.sabuj.spring.api.database.entity.Car duplicateCarEO = databaseManager.getCarFindByRegistrationId(car.getRegistrationId());
            if(duplicateCarEO != null 
                    && !carEO.getRegistrationId().equalsIgnoreCase(duplicateCarEO.getRegistrationId())){
                return new ServiceError(Utils.ERROR_VALUE_DUPLICATE, "car's registrationId="+car.getRegistrationId());
            }
            carEO.setRegistrationId(car.getRegistrationId());
        }
        if(!Utils.isEmpty(car.getMake())){
            carEO.setMake(car.getMake());
        }
        if(!Utils.isEmpty(car.getModel())){
            carEO.setModel(car.getModel());
        }
        if(!Utils.isEmpty(car.getYear())){
            carEO.setYear(Utils.getStringToDate(car.getYear()));
        }
        if(!Utils.isEmpty(car.getColor())){
            carEO.setColor(car.getColor());
        }
        if(!Utils.isEmpty(car.getDescription())){
            carEO.setDescription(car.getDescription());
        }
        
        carEO = databaseManager.updateCar(carEO);
        System.out.println("Car info updated for id: "+carEO.getCarId());
        
        List<com.sabuj.spring.api.database.entity.CarAttribute> attributeListEO = deleteThenSaveNewAttribute(carEO.getCarId(), car.getCarAttributeList());
        carEO.setCarAttributeList(attributeListEO);
        return new Car(carEO);
    }
    
    private List<com.sabuj.spring.api.database.entity.CarAttribute> deleteThenSaveNewAttribute(Integer carId, List<CarAttribute> attribute){
        if(!Utils.isEmpty(carId)){
            int total = databaseManager.deleteCarAttributes(carId);
            System.out.println("Total attributes deleted: "+total+" by car id: "+carId);
        }
        return saveAttributes(carId, attribute);
    }

    public Object deleteCar(Integer carId) {
        if(Utils.isEmpty(carId)){
            return new ServiceError(Utils.ERROR_NULL_VALUE, "carId");
        }
        
        com.sabuj.spring.api.database.entity.Car carEO = databaseManager.getCarFindById(carId);
        if(carEO == null){
            return new ServiceError(Utils.ERROR_VALUE_INVALID, "cardId="+carId);
        }
        System.out.println("Found card record from DB for id: "+carId);
        
        Integer count = databaseManager.deleteCar(carId);
        System.out.println("Total records deleted for "+carId+" is : "+count);
        
        int total = databaseManager.deleteCarAttributes(carId);
        System.out.println("Total records deleted from Attribute for "+carId+" is : "+total);
        
        return count;
    }

    public Object getCar(CarSearchCriteria criteria) {
        if(criteria == null){
            return new ServiceError(Utils.ERROR_NULL_VALUE, "search criteria");
        }
        criteria.removeQuotes();
        System.out.println("Search criteria provided is: "+criteria);
        
        String sql = "select o from Car o ";
        String condition = "";
        if(!Utils.isEmpty(criteria.getCarId())){
            condition += " o.carId = "+criteria.getCarId() + " and ";
	}
        if(!Utils.isEmpty(criteria.getRegistrationId())){
            condition += " o.registrationId = '"+criteria.getRegistrationId() + "' and ";
	}
        if(!Utils.isEmpty(criteria.getMake())){
            condition += " o.make = '"+criteria.getMake() + "' and ";
	}
        if(!Utils.isEmpty(criteria.getModel())){
            condition += " o.model = '"+criteria.getModel() + "' and ";
	}
        if(!Utils.isEmpty(criteria.getColor())){
            condition += " o.color = '"+criteria.getColor() + "' and ";
	}
        if(!Utils.isEmpty(condition)){
            condition = condition.replaceAll("(?i)and\\s*$", "");
            sql = sql + " where "+condition;
        }
        System.out.println("Sql query: <"+sql+">");
        
        List<com.sabuj.spring.api.database.entity.Car> carListEO = databaseManager.getCarsFindBySql(sql);
        List<Car> carListBO = new ArrayList<Car>();
        
        if(carListEO != null){
            System.out.println("Total car found: "+carListEO.size());
            for(com.sabuj.spring.api.database.entity.Car carEO : carListEO){
                carListBO.add(new Car(carEO));
            }
        }
        
        Integer total = carListBO.size();
        Object []objectArray = new Object[]{carListBO, total};
        return objectArray;
    }
}
