package com.navertool.web;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueUtil {

    private static Logger m_logger = LoggerFactory.getLogger(ValueUtil.class);


    
    public static String display(Object val) {
        
        if ( val == null) return "";
        
        return val.toString();
        
    }

//    public static String display(BigDecimal val) {
//        
//        if ( val == null) return "";
//        
//        return val.toString();
//    }    
    
    public static String display(Object val, String defaultValue) {
        
        if ( val == null) {
            if ( defaultValue == null) 
                return "";
            return defaultValue;
        }

        String str = val.toString();
        
        if ( str.trim().isEmpty()) 
            return defaultValue;
        
        return str.toString();
        
    }
    
    public static String displayDate(Object val, String defaultValue) {
        
        if ( val == null) {
            if ( defaultValue == null) 
                return "";
            return defaultValue;
        }

        String str = val.toString();
        
        if ( str.trim().isEmpty()) 
            return defaultValue;

        
        return DateTimeFormat.forPattern("yyyyMMdd-HH:mm:ss").parseDateTime(str).toString("yyyyMMdd");
        
    }
    
    
    public static String display(Boolean val, String trueVal, String falseVal) {
        
        if ( val == null || !val.booleanValue())
            return display(falseVal);
        
        return display(trueVal);
    }
    
    public static long getTimestamp(String date) {
        
        if (date == null) 
            return -1;
        
        try {
            return DateTimeFormat.forPattern("yyyyMMdd-HH:mm:ss").parseDateTime(date).toDate().getTime();
        }
        catch (Exception e) {
            m_logger.error(e.getMessage() ,e);
            return -1;
        }
    }
    
    public static String getCurrentTimestamp() {
        
        
        return DateTime.now().toString("yyyyMMdd-HH:mm:ss");
    }
    
    
     public  static <T> T convertFromString(Class<T> clazz, String value) {
        
        if (clazz == BigDecimal.class ) {
            if ( value == null) return null;
            
            try {
                return (T) new BigDecimal(value);
            }
            catch (Exception e) {
                return null;
            }
        } else if ( clazz == Double.class ){

            if ( value == null) return null;
            
            try {
                return (T) new Double(value);
            }
            catch (Exception e) {
                return null;
            }

        }
        
        return null;
    }
     
     
    public static int convertToGuaranteedInt(String val, int defaultVal) {
        
        try {
            return Integer.parseInt(val);
        }
        catch (NumberFormatException e) {
            return defaultVal;
        }
    }
     
    public static double convertToGuaranteedDouble(String val, double defaultVal) {
        
        try {
            return Double.parseDouble(val);
        }
        catch (NumberFormatException e) {
            return defaultVal;
        }
    }
     
    
    
}
