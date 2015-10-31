package com.sabuj.spring.api.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 
 * @author Sabuj
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Car.findById", query = "select o from Car o where o.carId = :carId"),
    @NamedQuery(name = "Car.findByRegistrationId", query = "select o from Car o where o.registrationId = :registrationId")
})
@Table(name = "springdb_car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="car_id")
    private int carId;
    
    @Column(name="car_registration_id")
    private String registrationId;
    
    @Column(name="car_make")
    private String make;
    
    @Column(name="car_model")
    private String model;
    
    @Column(name="car_year")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date year;
    
    @Column(name="car_color")
    private String color;
    
    @Column(name="car_description", columnDefinition="text")
    private String description;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carId")
    private List<CarAttribute> carAttributeList;

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
    public Date getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Date year) {
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
