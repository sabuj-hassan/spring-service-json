package com.sabuj.spring.api.business.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Sabuj
 */
public class Utils {
    public static final int ERROR_NULL_VALUE = 100;
    public static final int ERROR_VALUE_MISSING = 101;
    public static final int ERROR_VALUE_INVALID = 102;
    public static final int ERROR_VALUE_DUPLICATE = 103;
    public static final int ERROR_INVALID_JSON = 901;
    
    public static final int CACHE_EXPIRY_DAY = 2;
    
    public static boolean isEmpty(String string){
        if(string == null || string.equals("")){
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(Integer integer){
        if(integer == null || integer.intValue() == 0){
            return true;
        }
        return false;
    }
    
    public static Date getStringToDate(String stringDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(stringDate);
        } catch (Exception x) {
            x.printStackTrace();
        }
        
        return null;
    }
    
    public static String getFormattedDate(Date date) {
        if (date == null) {
            return "";
        }
        String format = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    public static Object getObject(String json, Class className){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, className);
        }catch(Exception e){
            return null;
        }
    }
    
    public static String getJson(Object object){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        }catch(Exception e){
            return "";
        }
    }

    
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }
}
