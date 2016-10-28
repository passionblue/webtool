package com.navertool.web;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    private static Logger m_logger = LoggerFactory.getLogger(DateUtil.class);

    // Default Constructor
    public DateUtil() {
    }

    
    public static boolean warnForShippingTime(String date) {
        
        if ( date == null) 
            return false;
        
        long time = ValueUtil.getTimestamp(date);
        
        
        long warnLimit = 5*3600*1000;
        
        
        
        if ( time - System.currentTimeMillis() < warnLimit)  
            return true;
        
        return false;
    }
    
    
    public static String getTodayDate() {
        
        return DateTime.now().toString("MM/dd");
        
    }
    
}
