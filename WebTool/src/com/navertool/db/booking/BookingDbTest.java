package com.navertool.db.booking;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookingDbTest {

    private static Logger m_logger = LoggerFactory.getLogger(BookingDbTest.class);

    //Default Constructor
    public BookingDbTest() {
    }
    
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-hsql-booking.xml");
        
        //fixMsgPersistService was not defined in the config file but the class has Component annontation
        NaverBookingDataPersistService fixMsgPersistService = (NaverBookingDataPersistService) context.getBean("naverBookingDataPersistService"); 
        
        
        List<FileProcessHistory> all = fixMsgPersistService.getNaverBookingDao().getAllFileHistory();

        
    }
    
    public static void fixNoteTimestamp() {
        
    }

}
