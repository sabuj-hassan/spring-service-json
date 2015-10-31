package com.sabuj.spring.api.business.entity;

import java.util.Date;

/**
 * 
 * @author Sabuj
 */
public class CarSearchCriteria {
    private int carId;
    private String registrationId;
    private String make;
    private String model;
    private Date year;
    private String color;

    public CarSearchCriteria(){
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
    public Date getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Date year) {
        this.year = year;
    }
    
    @Override
    public String toString(){
        return "carId: "+carId+"\nregistration id: "+registrationId+"\nmodel: "+model+"\nmake: "+make+"\nyear: "+year+"\ncolor: "+color;
    }
    
    public void removeQuotes(){
        if(this.model != null){
            this.model.replace("'", "");
        }
        if(this.make != null){
            this.make.replace("'", "");
        }
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
}
