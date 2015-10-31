package com.sabuj.spring.api.business.entity;

/**
 * 
 * @author Sabuj
 */
public class CarAttribute {

    private Integer attributeId;
    private Integer carId;
    private String key;
    private String value;

    public CarAttribute() {
    }
    
    public CarAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public CarAttribute(com.sabuj.spring.api.database.entity.CarAttribute carAttributeEO) {
        if (carAttributeEO != null) {
            attributeId = carAttributeEO.getAttributeId();
            carId = carAttributeEO.getCarId();
            key = carAttributeEO.getKey();
            value = carAttributeEO.getValue();
        }
    }

    /**
     * @return the attributeId
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * @param attributeId the attributeId to set
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * @return the carId
     */
    public Integer getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
}
