package com.sabuj.spring.api.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Sabuj
 */
@Entity
@Table(name = "springdb_car_attributes")
public class CarAttribute implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="attr_id")
    private Integer attributeId;
    
    @Column(name="attr_car_id")
    private Integer carId;
    
    @Column(name="attr_key")
    private String key;
    
    @Column(name="attr_value")
    private String value;

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
