package com.navertool.excelprocessor.poi;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.LastModified;

public class Batch {

    private static Logger m_logger = LoggerFactory.getLogger(Batch.class);

    // Default Constructor
    public Batch() {
    }

    public static void main(String[] args) throws Exception {
        
        
        String targetDirectory  = "D:\\NaverDev\\data";
        
        List<File> listAll = FileGrabUtils.getFilesByMatching(targetDirectory, "스토어팜_전체주문조회");

        for (File file : listAll) {
            
            BaseNaverExcelDataHandler h = new NaverOrderHandler(file.getAbsolutePath());
            h.processSheet();
            
            m_logger.info("Processing file {}", file.getName());

        }
        
        List<File> listShipping = FileGrabUtils.getFilesByMatching(targetDirectory, "스토어팜_전체주문배송현황");

        for (File file : listShipping) {
            
            BaseNaverExcelDataHandler h = new NaverOrderShippingData(file.getAbsolutePath());
            h.processSheet();
            
            m_logger.info("Processing file {}", file.getName());

        }        
        
        
        List<File> listConfirmed = FileGrabUtils.getFilesByMatching(targetDirectory, "스토어팜_구매확정내역");
        
        for (File file : listConfirmed) {
            
            BaseNaverExcelDataHandler h = new NaverOrderHandler(file.getAbsolutePath());
            h.processSheet();
            
            m_logger.info("Processing file {}", file.getName());

        }        

        List<File> listPurchase = FileGrabUtils.getFilesByMatching(targetDirectory, "구입");

//        for (File file : listPurchase) {
//            
//            NaverPurchaseDataHandler h = new NaverPurchaseDataHandler(file.getAbsolutePath());
//            h.processSheet();
//            
//            m_logger.info("Processing file {}", file.getName());
//
//        }        
        
        
        
        List<File> list2 = FileGrabUtils.getFilesByMatching(targetDirectory, "paySettleDailyDetail");


        List<File> list3 = FileGrabUtils.getFilesByMatching(targetDirectory, "SettleBenefitDetail");
        


    }
    
    public static void main2(String[] args) throws Exception {

        // IOFileFilter filte = FileFilterUtils.prefixFileFilter("스토어팜_전체주문조회");
        IOFileFilter filte = FileFilterUtils.prefixFileFilter("");

        List<File> list = (List<File>) FileUtils.listFiles(new File("D:\\NaverDev\\data"), filte, null);

        Collections.sort(list, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {

                return (int) (o1.lastModified() - o2.lastModified());
            }

        });

        for (File file : list) {
            m_logger.info("{}", file);
        }

    }
}
