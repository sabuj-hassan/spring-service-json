<?php

/**
 *
 * @author Sabuj
 */

$__FLAG = 2;
if($__FLAG == 0){
    
    $url = "http://localhost:8080/spring-service/createcar.json";
    $param = '{"car":{"carId":0,"registrationId":"RX32328801","make":"Toyota","model":"RAV4","year":"1991-10-10","color":"RED","description":"some desc text goes here!","carAttributeList":null}}';
    curl($url, $param);
    
}
else if($__FLAG == 1){
    
    $url = "http://localhost:8080/spring-service/updatecar.json";
    $param = '{"car":{"carId":15,"registrationId":null,"make":"Mazda","model":"PCXV4","year":"1999-10-10","color":null,"description":null,"carAttributeList":null}}';
    curl($url, $param);
    
}else if ($__FLAG == 2){
    
    $url = "http://localhost:8080/spring-service/deletecar.json";
    $param = '{"carId":15}';
    curl($url, $param);
    
}else if ($__FLAG == 3){

    $url = "http://localhost:8080/spring-service/getcar.json";
    $param = ' {"criteria":{"carId":0,"registrationId":"RX32328801","make":null,"model":null,"year":null,"color":null}}';
    curl($url, $param);
    
}else if ($__FLAG == 4){

    $url = "http://localhost:8080/spring-service/getcars.json";
    curl($url);
    
}

function curl($url, $param=""){
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL,$url);
    curl_setopt($ch, CURLOPT_USERAGENT,'Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)');
    curl_setopt($ch, CURLOPT_HEADER,true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    if($param != ""){
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $param);
    }
    curl_setopt($ch, CURLOPT_RETURNTRANSFER,true);
    curl_setopt($ch, CURLOPT_VERBOSE, true);
    $result = curl_exec($ch);
    print $result;
    curl_close($ch);
}


?>

