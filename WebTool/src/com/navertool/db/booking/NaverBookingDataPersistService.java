package com.navertool.db.booking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NaverBookingDataPersistService {

    private static Logger m_logger = LoggerFactory.getLogger(NaverBookingDataPersistService.class);

    private NaverBookingDao naverBookingDao;

    // Default Constructor
    public NaverBookingDataPersistService() {
    }

    public NaverBookingDao getNaverBookingDao() {
        return naverBookingDao;
    }

    @Autowired
    public void setNaverBookingDao(NaverBookingDao naverBookingDao) {
        this.naverBookingDao = naverBookingDao;
    }

    private static NaverBookingDataPersistService m_instance = new NaverBookingDataPersistService();

    public static synchronized NaverBookingDataPersistService getInstance() {

        if (m_instance == null) {

            ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-hsql-booking.xml");

            m_instance = (NaverBookingDataPersistService) context.getBean("naverBookingDataPersistService");

        }

        return m_instance;
    }

    public static void main(String[] args) throws Exception {

        NaverBookingDataPersistService persistService = NaverBookingDataPersistService.getInstance();

        List<FileProcessHistory> all = persistService.getNaverBookingDao().getAllFileHistory();

        for (FileProcessHistory record : all) {
            m_logger.debug(" FileProcessHistory {}", record);
        }

    }
}
