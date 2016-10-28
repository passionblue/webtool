package com.navertool.excelprocessor.poi;

import java.io.File;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.db.booking.FileProcessHistory;
import com.navertool.db.booking.NaverModel;
import com.navertool.db.booking.NaverOrder;

public class NaverSettleBenefitHandler extends BaseNaverExcelDataHandler {

    private static Logger m_logger = LoggerFactory.getLogger(NaverSettleBenefitHandler.class);

    // Default Constructor
    public NaverSettleBenefitHandler(String file) throws Exception {
        super(file);
    }

    @Override
    public NaverModel generateModel() {
        NaverModel model = new NaverOrder(this.titleType, this.titleDateType);
        return model;
    }

    public void processSheet() throws Exception {

        FileProcessHistory history = persistService.getNaverBookingDao().getHistory(titleType, titleDateType);
        
//      if ( history != null ) {
//          m_logger.error("ALREADY process {}", history );
//          return;
//      }
      
      int failCount = 0;
      for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

          Row row = worksheet.getRow(i);
          String orderNum = (String) getValueFromRow("orderNum", colums, row);
          
          NaverOrder naverOrder = (NaverOrder) persistService.getNaverBookingDao().getNaverOrderByOrderNum(orderNum);

          if ( naverOrder == null ) {
              
              m_logger.error("########### Naver Order not found for {}", orderNum);
              continue;
          }

          
          String benefitDetail = (String) getValueFromRow("benefitDetail", colums, row);
          Object benefitPaid = getValueFromRow("benefitPaid", colums, row);
          
          try {

              if ( "구매평 작성".equals(benefitDetail) ) {
                  BeanUtils.setProperty(naverOrder, "fee4", benefitPaid);
                  BeanUtils.setProperty(naverOrder, "fee4Detail", "구매평 작성");
              } else if ( "프리미엄 구매평 작성".equals(benefitDetail.trim()) ) {
                  BeanUtils.setProperty(naverOrder, "fee5", benefitPaid);
                  BeanUtils.setProperty(naverOrder, "fee5Detail", "프리미엄 구매평 작성");
              } else { // 찜
                  BeanUtils.setProperty(naverOrder, "fee6", benefitPaid);
                  BeanUtils.setProperty(naverOrder, "fee6Detail", "스토어찜");
                  
              }
              
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
      
      
      FileProcessHistory processHistoryEntry = new FileProcessHistory();
      processHistoryEntry.setDateProcessed(new DateTime(System.currentTimeMillis()).toString("yyyyMMdd"));
      processHistoryEntry.setFileName(fileName);
      processHistoryEntry.setRowCounts(worksheet.getPhysicalNumberOfRows()-1);
      processHistoryEntry.setTitleDate(titleDateType);
      processHistoryEntry.setTitleType(titleType);
      processHistoryEntry.setFailCounts(failCount);
      persistService.getNaverBookingDao().insert(processHistoryEntry);
        
        
    }

    public static void main(String[] args) throws Exception {

        String extentions[] = new String[]{"xls", "xlsx" };
        
        
        
        List<File> files = (List) FileUtils.listFiles(new File("D:\\NaverDev\\data"), extentions, false);        
        
        for (File file : files) {
            
            if ( file.getName().startsWith("SettleBenefitDetail") ||
                    file.getName().startsWith("SettleBenefitDetail") ){

                NaverSettleBenefitHandler h = new NaverSettleBenefitHandler(file.getAbsolutePath());
                h.processSheet();
                
                m_logger.info("Processing file {}", file.getName());

            }
            
        }         
        
        
        // BaseNaverExcelDataHandler h = new
        // NaverOrderHandler("D:\\NaverDev\\data\\스토어팜_전체주문조회_20160923_2047.xls");
//        NaverSettleBenefitHandler h = new NaverSettleBenefitHandler("D:\\NaverDev\\data\\SettleBenefitDetail_20160924.xlsx");
//        h.processSheet();
    }
}
