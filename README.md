# spring-service-json
This is a sample Spring service that accepts some JSON request and produces JSON response through the HTTP.

1) Minimul requirements:

    IDE used: Netbeans 7.0
    Java version: 1.8
    Mysql version: Ver 14.14 Distrib 5.1.45
    Apache-tomcat version: 6.0.44
    Apache Maven version: 3.3.3


2) [db-scripts\mysql.sql] contains the db creation scripts.

3) Code structure: It has three layers (service, business, database)

    a) service: the REST request comes to this layer.
    b) business: service layer forward the request to this layer.
    c) database: this layer helps business layer to do database operations.
    
4) To build the [.war] just click on project name on left panel, and select "clean-build". It will create [spring-service.war] inside the [target] folder. You can deploy it to your apache-tomcat server.

5) For my case the database credentials are as below. You can change this from the [WEB-INF\spring-servlet.xml] file.

    -- DATABASE NAME:: springdb
    -- DATABASE USER:: root
    -- DATABASE PASS:: mysql
    -- DATABASE SERVER:: 127.0.0.1
    -- DATABASE PORT:: 3306

6) The list of webservices are as below:

    /spring-service/createcar.json:
    /spring-service/updatecar.json:
    /spring-service/deletecar.json:
    /spring-service/getcar.json: return a list of Car objects based on a given search criteria.
    /spring-service/getcars.json: return all the cars from database



---------------------- SOME SAMPLE POST JSON I have used with php-CURL

    $url = "http://localhost:8080/spring-service/createcar.json";
    $param = '{"car":{"carId":0,"registrationId":"RX32328801","make":"Toyota","model":"RAV4","year":"1991-10-10","color":"RED","description":"some desc text goes here!","carAttributeList":null}}';


    $url = "http://localhost:8080/spring-service/updatecar.json";
    $param = '{"car":{"carId":15,"registrationId":null,"make":"Mazda","model":"PCXV4","year":"1999-10-10","color":null,"description":null,"carAttributeList":null}}';
    
    
    $url = "http://localhost:8080/spring-service/deletecar.json";
    $param = '{"carId":15}';
    

    $url = "http://localhost:8080/spring-service/getcar.json";
    $param = ' {"criteria":{"carId":0,"registrationId":"RX32328801","make":null,"model":null,"year":null,"color":null}}';
    
    
