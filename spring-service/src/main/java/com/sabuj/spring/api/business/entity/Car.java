package com.sabuj.spring.api.business.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sabuj
 */
public class Car {
    private int carId;
    private String registrationId;
    private String make;
    private String model;
    private String year;
    private String color;
    private String description;
    private List<CarAttribute> carAttributeList;

    public Car(){
        
    }
    
    public Car(com.sabuj.spring.api.database.entity.Car carEO){
        if(carEO != null){
            carId = carEO.getCarId();
            registrationId = carEO.getRegistrationId();
            make = carEO.getMake();
            model = carEO.getModel();
            color = carEO.getColor();
            year = Utils.getFormattedDate(carEO.getYear());
            description = carEO.getDescription();
            
            if(carEO.getCarAttributeList() != null){
                carAttributeList = new ArrayList<CarAttribute>();
                for(com.sabuj.spring.api.database.entity.CarAttribute attribute : carEO.getCarAttributeList()){
                    carAttributeList.add(new CarAttribute(attribute));
                }
            }
        }
    }

    /**
     * @return the carId
     */
    public int getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * @return the registrationId
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId the registrationId to set
     */
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the carAttributeList
     */
    public List<CarAttribute> getCarAttributeList() {
        return carAttributeList;
    }

    /**
     * @param carAttributeList the carAttributeList to set
     */
    public void setCarAttributeList(List<CarAttribute> carAttributeList) {
        this.carAttributeList = carAttributeList;
    }

}
