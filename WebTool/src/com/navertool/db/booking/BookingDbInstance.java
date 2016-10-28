package com.navertool.db.booking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookingDbInstance {

    private static Logger m_logger = LoggerFactory.getLogger(BookingDbInstance.class);

    private NaverBookingDataPersistService m_sinstance = new NaverBookingDataPersistService();
    
    // Default Constructor
    public BookingDbInstance() {
        
        
        String bookingDBFile = System.getProperty("WEBTOOL_BOOKING_SPRING_CONFIG", "spring-config-hsql-booking.xml");
        
        ApplicationContext context = new ClassPathXmlApplicationContext(bookingDBFile);

        m_sinstance = (NaverBookingDataPersistService) context.getBean("naverBookingDataPersistService");
    }

    private static BookingDbInstance m_instance = new BookingDbInstance();

    public static NaverBookingDataPersistService getInstance() {
        return m_instance.m_sinstance;
    }

    public static void main(String[] args) throws Exception {

        NaverBookingDataPersistService persistService = BookingDbInstance.getInstance();

        // List<FileProcessHistory> all =
        // persistService.getNaverBookingDao().getAllFileHistory();
        //
        // for (FileProcessHistory record : all) {
        // m_logger.debug(" FileProcessHistory {}", record);
        // }
//
//        List<NaverOrder> all = persistService.getNaverBookingDao().getAllNaverOrder();
//
//        for (NaverOrder record : all) {
//            m_logger.debug(" FileProcessHistory {}", record);
//        }

        List<NaverOrder> result = persistService.getNaverBookingDao().getNaverOrderByNames("최우경");

        for (NaverOrder record : result) {
            m_logger.debug(" FileProcessHistory {}", record);
        }

//        
//        List<NaverOrder> result2 = persistService.getNaverBookingDao().getNaverOrderByProductNum("496053506");
//
//        for (NaverOrder record : result2) {
//            m_logger.debug(" FileProcessHistory {}", record);
//        }
        
    }

}
