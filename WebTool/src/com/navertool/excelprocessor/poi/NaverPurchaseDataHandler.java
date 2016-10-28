package com.navertool.excelprocessor.poi;
import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.FileProcessHistory;
import com.navertool.db.booking.NaverModel;
import com.navertool.db.booking.NaverOrder;
public class NaverPurchaseDataHandler extends BaseNaverExcelDataHandler {

    private static Logger m_logger = LoggerFactory.getLogger(NaverPurchaseDataHandler.class);

    
    // Default Constructor
    public NaverPurchaseDataHandler(String file) throws Exception {
        super(file);
    }
    
    

    @Override
    public NaverModel generateModel() {
        NaverModel model = new NaverOrder(this.titleType, this.titleDateType);
        return model;
    }

    
    
    public void processSheet() throws Exception {

      FileProcessHistory history = persistService.getNaverBookingDao().getHistory(titleType, titleDateType);
        
//        if ( history != null ) {
//            m_logger.error("ALREADY process {}", history );
//            return;
//        }
        
        int failCount = 0;
        int totalCount = 0;
        int skippedCount = 0;
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            
            totalCount++;
            Row row = worksheet.getRow(i);
            Object orderNumOrg = getValueFromRow("orderProductNum", colums, row);
            
            if ( orderNumOrg == null ) {
                skippedCount++;
                continue;
            }
                    
            String orderNum = getValueFromRow("orderProductNum", colums, row).toString();
            
            if ( orderNum == null || orderNum.length() < 2) {
                skippedCount++;
                continue;
            }
            
            orderNum = orderNum.subSequence(0,  orderNum.length()-1) + "1"; // hack
            
            NaverOrder naverOrder = (NaverOrder) persistService.getNaverBookingDao().getNaverOrderByOrderProductNum(orderNum);

            if ( naverOrder == null ) {
                m_logger.error("########### Naver Order not found for {}", orderNum);
                skippedCount++;
                continue;
            }

            
//            String priceOriginalProduct = (String) getValueFromRow("priceOriginalProduct", colums, row);
            Object priceOriginalProduct = getValueFromRow("priceOriginalProduct", colums, row);
//            Object feeAffiliate = getValueFromRow("feeAffiliate", colums, row);
            
            try {
                BeanUtils.setProperty(naverOrder, "priceOriginalProduct", priceOriginalProduct);
                BeanUtils.setProperty(naverOrder, "orderDetailStatus", "정산완료");
                
                naverOrder.incrementVersion();                
                naverOrder.setTimeUpdated(System.currentTimeMillis());
                m_logger.info("Order AFTER processed from row #{} - {}", i, naverOrder);
                
                persistService.getNaverBookingDao().update(naverOrder);
            }
            catch (Exception e) {
                m_logger.error(e.getMessage() ,e);
                failCount++;
            }
            
        }
        
        m_logger.info("Processing completed. file={}, processedCount={}, skipped={}, failed={}", fileName, totalCount, skippedCount, failCount);
        
        FileProcessHistory processHistoryEntry = new FileProcessHistory();
        processHistoryEntry.setDateProcessed(new DateTime(System.currentTimeMillis()).toString("yyyyMMdd"));
        processHistoryEntry.setFileName(fileName);
        processHistoryEntry.setRowCounts(totalCount);
        processHistoryEntry.setTitleDate(titleDateType);
        processHistoryEntry.setTitleType(titleType);
        processHistoryEntry.setFailCounts(failCount);
        persistService.getNaverBookingDao().insert(processHistoryEntry);
        
    }
    
    public static void main(String[] args) throws Exception {

        String extentions[] = new String[]{"xls", "xlsx" };
        
        
        
        List<File> files = (List) FileUtils.listFiles(new File("D:\\NaverDev\\data"), extentions, false);        
        
        for (File file : files) {
            
            if ( file.getName().startsWith("구입") ||
                    file.getName().startsWith("구입") ){

                NaverPurchaseDataHandler h = new NaverPurchaseDataHandler(file.getAbsolutePath());
                h.processSheet();
                
                m_logger.info("Processing file {}", file.getName());

            }
        }        
    }
}
