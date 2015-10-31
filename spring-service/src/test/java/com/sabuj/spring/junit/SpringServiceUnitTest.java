package com.sabuj.spring.junit;

import com.sabuj.spring.api.service.response.GetCarResponse;
import com.sabuj.spring.api.business.entity.CarSearchCriteria;
import com.sabuj.spring.api.service.request.GetCarRequest;
import com.sabuj.spring.api.service.response.UpdateCarResponse;
import com.sabuj.spring.api.service.request.UpdateCarRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabuj.spring.api.business.entity.CarAttribute;
import java.util.List;
import java.util.ArrayList;
import com.sabuj.spring.api.business.entity.Car;
import com.sabuj.spring.api.service.request.CreateCarRequest;
import com.sabuj.spring.api.service.response.CreateCarResponse;
import com.sabuj.spring.api.service.response.DeleteCarResponse;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sabuj
 */
public class SpringServiceUnitTest {
    
    private final String URL_CREATE = "http://localhost:8080/spring-service/createcar.json";
    private final String URL_UPDATE = "http://localhost:8080/spring-service/updatecar.json";
    private final String URL_DELETE = "http://localhost:8080/spring-service/deletecar.json";
    private final String URL_GET = "http://localhost:8080/spring-service/getcar.json";
    
    public SpringServiceUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    // @Test
    public void manageCar() {
        // Creating car without entity
        System.out.println("\nCreating a car with no attribute");
        CreateCarRequest createCarRequest = new CreateCarRequest();
        Car car = new Car();
        car.setRegistrationId("RX32328801");
        car.setMake("Toyota");
        car.setModel("RAV4");
        car.setYear("1991-10-10");
        car.setColor("RED");
        car.setDescription("some desc text goes here!");
        createCarRequest.setCar(car);
        System.out.println("Sending request\n "+this.getJson(createCarRequest));
        String responseJson = this.postData(this.URL_CREATE, this.getJson(createCarRequest));
        System.out.println("Response found\n "+responseJson);
        CreateCarResponse createCarResponse = (CreateCarResponse)getObject(responseJson, CreateCarResponse.class);
        assertNotNull("Response is not null", createCarResponse);
        assertNull("Error code is null", createCarResponse.getErrorCode());
        assertNotNull("Car object is not null", createCarResponse.getCar());
        assertNotNull("Car id has created", createCarResponse.getCar().getCarId());
        System.out.println("A car has created with pk id: "+createCarResponse.getCar().getCarId());
        
        // Updating the car
        System.out.println("\nUpdating the car record now");
        UpdateCarRequest updateRequest = new UpdateCarRequest();
        Car updateCar = new Car();
        updateCar.setCarId(createCarResponse.getCar().getCarId());
        updateCar.setMake("Mazda");
        updateCar.setModel("PCXV4");
        updateCar.setYear("1999-10-10");
        updateRequest.setCar(updateCar);
        System.out.println("Sending request\n "+this.getJson(updateRequest));
        responseJson = this.postData(this.URL_UPDATE, this.getJson(updateRequest));
        System.out.println("Response found\n "+responseJson);
        UpdateCarResponse updateCarResponse = (UpdateCarResponse)getObject(responseJson, UpdateCarResponse.class);
        assertNotNull("Response is not null", updateCarResponse);
        assertNull("Error code is null", updateCarResponse.getErrorCode());
        assertNotNull("Car object is not null", updateCarResponse.getCar());
        assertNotNull("Car's Make is not null", updateCarResponse.getCar().getMake());
        assertNotNull("Car's Model is not null", updateCarResponse.getCar().getModel());
        assertNotNull("Car's Year is not null", updateCarResponse.getCar().getYear());
        assertNotSame("Car's make has updated", updateCar.getMake(), updateCarResponse.getCar().getMake());
        assertNotSame("Car's model has updated", updateCar.getModel(), updateCarResponse.getCar().getModel());
        assertNotSame("Car's year has updated", updateCar.getYear(), updateCarResponse.getCar().getYear());
        
        // Deleting the car
        System.out.println("\nDeleting the car record now");
        responseJson = this.postData(this.URL_DELETE, "{\"carId\":"+createCarResponse.getCar().getCarId()+"}");
        System.out.println("Response found\n "+responseJson);
        DeleteCarResponse deleteCarResponse = (DeleteCarResponse)getObject(responseJson, DeleteCarResponse.class);
        assertNotNull("Response is not null", deleteCarResponse);
        assertNull("Error code is null", deleteCarResponse.getErrorCode());
        assertNotNull("Deleted row count is null", deleteCarResponse.getCount());
        assertEquals("Total record deleted", (int)1, deleteCarResponse.getCount().intValue());
        
        // Creating car with optional attribute
        System.out.println("\nCreating another car, this time with optional attributes");
        List<CarAttribute> attributeList = new ArrayList<CarAttribute>();
        attributeList.add(new CarAttribute("Previous Owner", "Alexi Alex"));
        attributeList.add(new CarAttribute("Car CC", "2000cc"));
        car.setCarAttributeList(attributeList);
        createCarRequest.setCar(car);
        System.out.println("Sending request\n "+this.getJson(createCarRequest));
        responseJson = this.postData(this.URL_CREATE, this.getJson(createCarRequest));
        System.out.println("Response found\n "+responseJson);
        createCarResponse = (CreateCarResponse)getObject(responseJson, CreateCarResponse.class);
        assertNotNull("Response is not null", createCarResponse);
        assertNull("Error code is null", createCarResponse.getErrorCode());
        assertNotNull("Car object is not null", createCarResponse.getCar());
        assertNotNull("Car id has created", createCarResponse.getCar().getCarId());
        assertNotNull("Car attribute list not null", createCarResponse.getCar().getCarAttributeList());
        assertNotNull("Car attribute list has data at 0", createCarResponse.getCar().getCarAttributeList().get(0));
        assertNotNull("Car attribute list has key at 0", createCarResponse.getCar().getCarAttributeList().get(0).getKey());
        assertNotNull("Car attribute list has value at 0", createCarResponse.getCar().getCarAttributeList().get(0).getValue());
        System.out.println("A car has created with pk id: "+createCarResponse.getCar().getCarId());
        System.out.println("Car's one of the attribute Key is "+createCarResponse.getCar().getCarAttributeList().get(0).getKey());
        System.out.println("Car's one of the attribute Value is "+createCarResponse.getCar().getCarAttributeList().get(0).getValue());
     

        // Search the car detail
        System.out.println("\nSearching a car for registration id: "+car.getRegistrationId());
        GetCarRequest getCarRequest = new GetCarRequest();
        CarSearchCriteria criteria = new CarSearchCriteria();
        criteria.setRegistrationId(car.getRegistrationId());
        getCarRequest.setCriteria(criteria);
        System.out.println("Sending request\n "+this.getJson(getCarRequest));
        responseJson = this.postData(this.URL_GET, this.getJson(getCarRequest));
        System.out.println("Response found\n "+responseJson);
        GetCarResponse getCarResponse = (GetCarResponse)getObject(responseJson, GetCarResponse.class);
        assertNotNull("Response is not null", getCarResponse);
        assertNull("Error code is null", getCarResponse.getErrorCode());
        assertNotNull("Car list is not null", getCarResponse.getCarList());
        assertNotNull("Car list has value at index 0", getCarResponse.getCarList().get(0));
        assertNotNull("Car id has created", getCarResponse.getCarList().get(0).getRegistrationId());
        
        // Deleting the car
        System.out.println("\nDeleting the car record now");
        responseJson = this.postData(this.URL_DELETE, "{\"carId\":"+createCarResponse.getCar().getCarId()+"}");
        System.out.println("Response found\n "+responseJson);
        deleteCarResponse = (DeleteCarResponse)getObject(responseJson, DeleteCarResponse.class);
        assertNotNull("Response is not null", deleteCarResponse);
        assertNull("Error code is null", deleteCarResponse.getErrorCode());
        assertNotNull("Deleted row count is null", deleteCarResponse.getCount());
        assertEquals("Total record deleted", (int)1, deleteCarResponse.getCount().intValue());
        
        // Trying deleting deleted record once again
        System.out.println("\nTrying to delete an already deleted record");
        responseJson = this.postData(this.URL_DELETE, "{\"carId\":"+createCarResponse.getCar().getCarId()+"}");
        System.out.println("Response found\n "+responseJson);
        deleteCarResponse = (DeleteCarResponse)getObject(responseJson, DeleteCarResponse.class);
        assertNotNull("Response is not null", deleteCarResponse);
        assertNotNull("Error code is null", deleteCarResponse.getErrorCode());
        assertNotNull("Error code is null", deleteCarResponse.getErrorMsg());
    }
    
    private String getJson(Object object){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        }catch(Exception e){
            return "";
        }
    }
    
    private Object getObject(String json, Class className){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, className);
        }catch(Exception e){
            return null;
        }
    }
    
    private String postData(String url, String postData) {
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Developer Sabuj Hassan");
            con.setRequestProperty("Content-type", "application/json");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        }catch(Exception e){
            return "";
        }
    }
}
