
package com.sabuj.spring.api.database.manager;

import com.sabuj.spring.api.database.entity.Car;
import com.sabuj.spring.api.database.entity.CarAttribute;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Sabuj
 */
@Service
public class DatabaseManagerImpl implements DatabaseManager{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Car> getAll() {
        List<Car> result = em.createQuery("SELECT o FROM Car o", Car.class).getResultList();
        return result;
    }

    @Transactional
    public Car saveCar(Car car) {
        em.persist(car);
        return car;
    }

    @Transactional
    public Car getCarFindById(Integer carId) {
        List<Car> carList = em.createNamedQuery("Car.findById").setParameter("carId", carId).getResultList();
        if(carList != null && carList.size() > 0){
            return carList.get(0);
        }
        return null;
    }
    
    @Transactional
    public Car getCarFindByRegistrationId(String registrationId) {
        List<Car> carList = em.createNamedQuery("Car.findByRegistrationId").setParameter("registrationId", registrationId).getResultList();
        if(carList != null && carList.size() > 0){
            return carList.get(0);
        }
        return null;
    }

    @Transactional
    public Car updateCar(Car car) {
        em.merge(car);
        return car;
    }

    @Transactional
    public Integer deleteCar(Integer carId) {
        String query = "delete from Car o where o.carId = "+carId;
        return em.createQuery(query).executeUpdate();
    }

    @Transactional
    public List<Car> getCarsFindBySql(String sql) {
        return em.createQuery(sql).getResultList();
    }

    @Transactional
    public CarAttribute saveCarAttribute(CarAttribute attribute) {
        em.persist(attribute);
        return attribute;
    }

    @Transactional
    public Integer deleteCarAttributes(Integer carId) {
        String query = "delete from CarAttribute o where o.carId = "+carId;
        return em.createQuery(query).executeUpdate();
    }
}
