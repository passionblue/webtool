package com.navertool.db.booking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.navertool.db.booking.FileProcessHistory;
import com.navertool.db.booking.NaverBookingDataPersistService;
import com.navertool.db.booking.NaverOrder;

public class DisplayFileHistory {

    private static Logger m_logger = LoggerFactory.getLogger(DisplayFileHistory.class);

    // Default Constructor
    public DisplayFileHistory() {
    }

    public static void main(String[] args) throws Exception {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-hsql-booking.xml");
        
        NaverBookingDataPersistService persistService = (NaverBookingDataPersistService) context.getBean("naverBookingDataPersistService");
        
        List<FileProcessHistory> all = persistService.getNaverBookingDao().getAllFileHistory();
        
        
        
        for (FileProcessHistory record : all) {
            m_logger.debug(" FileProcessHistory {}", record);
        } 

        
        
        m_logger.debug("Num of counts {} ", all.size());
    }
}
